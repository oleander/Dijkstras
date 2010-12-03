public class Edge implements Comparable {
  private GraphNode destination;
  private int weight;
  private int line;
  
  public Edge(GraphNode dest, int line) {
    this(dest,Integer.MAX_VALUE, line);
  }
  
  public Edge(GraphNode dest, int weight, int line) {
    this.destination  = dest;
    this.weight       = weight;
    this.line         = line;
  }
  
  public GraphNode getDestination() {
    return this.destination;
  }
  
  public int getWeight() {
    return this.weight;
  }
  
  public int getLine() {
    return this.line;
  }
  
  /* Compares two edges with respect to their weights */
  public int compareTo(Object other) {
    if (other instanceof Edge) {
      Edge o = (Edge) other;
      return Integer.valueOf(this.weight).compareTo(o.getWeight());
    } else {
      throw new IllegalArgumentException();
    }
  }
  
  public String toString() {
    return "Destination: " + destination.getName() + ", time: " + this.weight +", line: " + this.line;
  }
}