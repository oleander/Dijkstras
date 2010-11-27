class Edge implements Comparable {
  private Node destination;
  private int weight;
  private int line;
  
  public Edge(Node dest, int weight, int line) {
    this.destination  = dest;
    this.weight       = weight;
    this.line         = line;
  }
  
  public Node getDestination() {
    return this.destination;
  }
  
  public int getWeight() {
    return this.weight;
  }
  
  public int getLine() {
    return this.line;
  }
  
  public int compareTo(Object other) {
    if (other instanceof Edge) {
      Edge o = (Edge) other;
      return Integer.valueOf(this.weight).compareTo(o.getWeight());
    } else {
      throw new IllegalArgumentException();
    }
  }
}