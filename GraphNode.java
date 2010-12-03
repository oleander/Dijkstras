import java.util.*;
import Lab3Help.BStop;
/* A graph node */

class GraphNode {
  private ArrayList<Edge> edgeList;
  private BStop stop;
  public GraphNode(BStop stop){
    this.edgeList = new ArrayList<Edge>();
    this.stop = stop;
  }
  
  public String getName(){
    return this.stop.getName();
  }
  
  public void addEdge(Edge edge){
    this.edgeList.add(edge);
  }
  
  public ArrayList<Edge> getEdges(){
    return this.edgeList;
  }
  
  public BStop getStop() {
    return this.stop;
  }
  
  public boolean equals(Object other) {
    if (other == null) {
      return false;
    } else if (this == other) {
      return true;
    } else if (other instanceof GraphNode) {
      GraphNode otherNode = (GraphNode) other;
      return (this.stop.getName().equals(otherNode.stop.getName()));
    }
    return false;
  }
  
  public int hashCode() {
    return this.stop.getName().hashCode();
  }
}