import java.util.*;
import Lab3Help.*;

/**
* Calculates the shortest paths between nodes in a graph.
*/

public class DijkPath implements Path {
  
  /* The graph from which the paths are calculated */
  private Graph graph;
  
  /* The length of the current path */
  private int length;
  
  private HashSet<Node<GraphNode>> visited = new HashSet<Node<GraphNode>>();
  
  Deque<Node<GraphNode>> path = new ArrayDeque<Node<GraphNode>>();
  
  
  /* Both constructors build a graph based on the input */
  public DijkPath(String stopsFileName, String linesFileName) {
    Lab3File l             = new Lab3File();
    List<BLineTable> lines = l.readLines(linesFileName);
    List<BStop> stops      = l.readStops(stopsFileName);
    
    this.buildGraph(stops,lines);  
  }
  
  public DijkPath(List<BStop> stops, List<BLineTable> lines) {
    this.buildGraph(stops,lines);
  }
  
  private void buildGraph(List<BStop> stops, List<BLineTable> lines){
    this.graph = new Graph();
    
    /* Skapar noder av alla hållplatser */
    for(BStop bs : stops){
      this.graph.addNode(new GraphNode(bs));
    }
    
    for(BLineTable blt : lines){
      
      BLineStop[] i = blt.getStops();
      BLineStop current = i[0]; 
      BLineStop next;
      Edge edge;
      
      /* Går igenom linjehållplatserna parvis. Den första hållplatsen i paret är ursprungsnoden, och den andra  
       * blir destinationsnoden för den nya bågen.
       */
      for (int n = 1; n < i.length; n++) {
        next = i[n];
        edge = new Edge(this.graph.getNode(next.getName()), next.getTime(), blt.getLineNo());
        GraphNode graphNode = this.graph.getNode(current.getName());
        
        if(graphNode == null){
          System.err.println("Det finns hållplatser som ej har linjer");
          System.exit(1);
        } else {
          graphNode.addEdge(edge);
        }
        
        current = next;
      }
    }
  }
  
  public void computePath(String from, String to) {
    
    /* Start- och målnoder */
    GraphNode start = this.graph.getNode(from);
    GraphNode end   = this.graph.getNode(to);
    
    /* Prioritetskön som håller reda på kortast avstånd från start till alla noder */
    PriorityQueue<GraphNode> pq = new PriorityQueue<GraphNode>(PriorityQueue.ASC);
    
    /* Nodemap låter oss hitta den pq-nod som inkapslar en given grafnod */
    HashMap<GraphNode,Node<GraphNode>> nodeMap = new HashMap<GraphNode,Node<GraphNode>>();
    
    /* Variabler för iterering */
    Node<GraphNode> current = null;
    Node<GraphNode> tempDest;
    int newDist;
    
    /* Tömmer path så att vi kan returnera en tom iterator om en väg till målet inte hittas. */
    path.clear();
    
    /* Inkapsla alla grafnoder i grafen i noder och lägg dem i en PQ. */
    for(GraphNode gn : this.graph.getNodes()){
      /* Alla nycklar sätts till ett väldigt stort värde */
      Node<GraphNode> n = new Node<GraphNode>(gn, Integer.MAX_VALUE);
      /* Ser till att vi kan hitta motsvarande nod med grafnoden som nyckel. */
      nodeMap.put(gn,n);
      pq.add(n); 
    }
    
    /* Hitta startnoden och ändra dess nyckel till noll */
    pq.update(nodeMap.get(start), 0);
    
    /* Hitta slutnoden och spara undan den för jämförelse i Dijkstras algoritm. */
    Node<GraphNode> destinationNode = nodeMap.get(end);
    
    /* Dijstras algoritm. */
    while(!pq.isEmpty()){
      current = pq.pull();
      
      // Har vi hittat vår slutnod?
      if(current.equals(destinationNode)){
        break; 
      }
      
      /* Itererar genom bågarna för att hitta alla möjliga destinationsnoder */
      for(Edge e : current.getValue().getEdges()){
        /* Hämtar den Nod som inkapslar bågens destinationsgrafnod. */
        tempDest = nodeMap.get(e.getDestination());
        
        /* Om noden redan har är besökt gör vi inget */
        if(visited.contains(tempDest)) {
          continue;
        }
        
        /* Räknar ut den nya längden. */
        newDist = current.getKey() + e.getWeight();
        
        /* Om den nya längden är kortare uppdaterar vi nodens nyckel i pq:n, samt registrerar vilken nod vi kom från
         * samt vilken linje som användes för resan.
         */
        if (tempDest.getKey() > newDist) {
          tempDest.setPrevious(current);
          tempDest.setLine(e.getLine());
          tempDest.setTimeFromPrevious(e.getWeight());
          pq.update(tempDest, newDist);
        }
      }
      /* Nu är vi klara med noden Current - den läggs till i visitedlistan */
      visited.add(current);
    }
    
    /* Hämtar destinationsnoden från nodeMap och lägger den i current */
    current = nodeMap.get(end);
    
    /* Currents nyckel är det kortaste avståndet till destinationen. Sparar det i length. */
    this.length = current.getKey();
    
    /* Går igenom kedjan av noder från destinationsnoden och bakåt för att bygga upp kortaste vägen till destinationen. */
    do {
      path.push(current);
      current = current.getPrevious();
    } while (current != null);
  }
  
  public Iterator<Node<GraphNode>> getPath() {
    return this.path.iterator();
  }
  
  public Iterator<BStop> getAllStops(){
    return this.graph.getStops().iterator();
  }
  
  public int getPathLength() {
    return this.length;
  }
  
  public Iterator<GraphNode> getNodes() {
    return this.graph.getNodes().iterator();
  }
}