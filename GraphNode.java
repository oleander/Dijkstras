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
  
  /**
   * @return Namnet på hållplatsen i noden
   */
  public String getName(){
    return this.stop.getName();
  }
  
  /**
   * Lägger till en utgående båge i grafnoden
   * @param edge En utgående båge
   */
  public void addEdge(Edge edge){
    this.edgeList.add(edge);
  }
  
  /**
   * @return En lista med bågar som utgår från noden
   */
  public ArrayList<Edge> getEdges(){
    return this.edgeList;
  }
  
  /**
   * @return Hållplatsen som finns i grafnoden
   */
  public BStop getStop() {
    return this.stop;
  }
  
  /* Likhet råder om hållplatserna har samma namn. */
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
  
  /**
   * Hashkoden för en grafnod bygger endast på namnet på hållplatsen som inkapslas.
   * @return Hashkod för grafnoden i fråga.
   */
  public int hashCode() {
    return this.stop.getName().hashCode();
  }
  
  /**
   * @return En strängrepresentation av grafnoden, dess innehåll och dess utgående bågar.
   */
  public String toString() {
    String output = stop.getName() + " - Outgoing edges:\n";
    for (Edge e : edgeList) {
      output += e + "\n";
    }
    return output;
  }
  
}