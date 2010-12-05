
/**
 * En båge som mellan två grafnoder
 * Håller reda på längd, destination och vilken linje som bågen representerar.
 */

public class Edge implements Comparable {
  private GraphNode destination;
  private GraphNode origin;
  private int weight;
  private int line;
  
  /**
   * Bygger en båge med maximal möjlig tyngd.
   * @param dest Bågens destinationsnod
   * @param line Bågens linje
   */
  public Edge(GraphNode dest, int line) {
    this(dest,Integer.MAX_VALUE, line);
  }
  
  /**
   * Bygger en båge.
   * @param dest Bågens destinationsnod
   * @param line Bågens linje
   * @param weight Bågens tyngd
   */
  public Edge(GraphNode dest, int weight, int line) {
    this.destination  = dest;
    this.weight       = weight;
    this.line         = line;
    this.origin       = null;
  }
  
  /**
   * Bygger en båge med en ursprungsnod.
   * @param origin Bågens ursprungsnod
   * @param dest Bågens destinationsnod
   * @param line Bågens linje
   * @param weight Bågens tyngd
   */
  public Edge(GraphNode origin, GraphNode dest, int weight, int line) {
    this(dest, weight, line);
    this.origin = origin;
  }
  
  /**
   * Ställer in bågens ursprungsnod
   * @param n Bågens ursprungsnod
   */
  public void setOrigin(GraphNode n) {
    this.origin = n;
  }
  
  /**
   * @return Bågens destinationsnod.
   */
  public GraphNode getDestination() {
    return this.destination;
  }
  
  /**
   * @return Bågens vikt, det vill säga tiden det tar att färdas längs bågen.
   */
  public int getWeight() {
    return this.weight;
  }
  
  /**
   * @return Linjen som bågen representerar.
   */
  public int getLine() {
    return this.line;
  }
  
  /**
   * När man jämför två bågar jämförs deras längder.
   */
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