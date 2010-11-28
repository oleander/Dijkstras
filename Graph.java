/* A graph */

class Graph {
  /* Vår prioritetskö är ju en bra lösning för en sorterad lista... */
  private PriorityQueue nodeList;
  private int size;
  
  public Graph(){
    nodeList = new PriorityQueue(PriorityQueue.ASC);
    size = 0;
  }
  
  public void addNode(GraphNode n);
}