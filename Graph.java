import java.util.HashSet;
/* A graph */

class Graph {
  private HashSet<Node> nodeSet;
  
  public Graph(){
    nodeList = new HashSet<Node>();
  }
  
  public void addNode(Node n) {
    this.nodeSet.add(n);
  }
}