import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import Lab3Help.BStop;

/**
 * En graf. Grafnoder innehåller busshållerplatser. Det går att komma åt grafnoder snabbt med hjälp av deras hållplatsnamn.
 * Bygger på adjacency lists. Innehåller en båglista, men varje båge innehåller referenser till destination, vilket gör det till
 * en adjacency list.
 */

public class Graph {
  private HashMap<String,GraphNode> posList;
  private ArrayList<GraphNode> nodeList;
  
  /**
   * Konstruerar en tom graf.
   */
  public Graph(){
    this.posList = new HashMap<String,GraphNode>();
    this.nodeList = new ArrayList<GraphNode>();
  }
  
  /**
   * Lägger till en nod i grafen.
   * @param gn Noden som ska läggas till.
   */
  public void addNode(GraphNode gn) {
    this.nodeList.add(gn);
    this.posList.put(gn.getName(), gn);
  }
  
  /**
   * Lägger till en båge på noden med namnet {from}.
   * @param from Namnet på noden som ska ha bågen.
   * @param edge Bågen som ska läggas till noden.
   */
  public void addEdge(String from, Edge edge){
    this.posList.get(from).addEdge(edge);
  }
  
  /**
   * @return Alla noder i grafen.
   */
  public ArrayList<GraphNode> getNodes(){
    return this.nodeList;
  }
  
  /**
   * Returnerar noden med det givna hållplatsnamnet.
   * @param name Namnet på hållplatsen som söks.
   */
  public GraphNode getNode(String name) {
    return this.posList.get(name);
  }
  
  /**
   * Returnerar en lista på alla busshållplatser som inkapslas i grafen.
   * @return Hållplatslista.
   */
  public List<BStop> getStops() {
    ArrayList<BStop> stops = new ArrayList<BStop>();
    for (GraphNode g : nodeList) {
      stops.add(g.getStop());
    }
    return stops;
  }
  
  public String toString(){
    String output = "";
    for (GraphNode g : nodeList) {
      output += g + "\n";
    }
    return output;
  }
}