import java.util.*;
/* A graph */

public class Graph {
  private HashMap<String,GraphNode> posList;
  private ArrayList<GraphNode> nodeList;
  
  public Graph(){
    this.posList = new HashMap<String,GraphNode>();
    this.nodeList = new ArrayList<GraphNode>();
  }
  
  public void addNode(GraphNode gn) {
    this.nodeList.add(gn);
    this.posList.put(gn.getName(), gn);
  }
  
  public void addEdge(String from, Edge edge){
    this.posList.get(from).addEdge(edge);
  }
  
  public ArrayList<GraphNode> getNodes(){
    return this.nodeList;
  }
  
  public GraphNode getNode(String name) {
    return this.posList.get(name);
  }
  
  public String toString(){
    String output = "";
    for (GraphNode g : nodeList) {
      output += g + "\n";
    }
    return output;
  }
}