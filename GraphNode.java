/**
 * En nod i en graf.
 */

public class GraphNode {
  private int key;
  private String value;
  
  /**
   * @param value Nyttovärdet i GraphNoden
   * @param key Nyckeln som används för att prioritera GraphNoden
   */
  public GraphNode(String value, int key){
    this.value = value;
    this.key = key;
  }
  
  /**
   * @return GraphNodens nyckel
   */
  public int getKey(){
    return this.key;
  }
  
  /**
   * @return GraphNodens nyttovärde
   */
  public String getValue(){
    return this.value;
  }
  
  /**
   * Ställer in GraphNodens nyttovärde
   * @param value Det nya värdet
   */
  public void setValue(String value){
    this.value = value;
  }
  
  /**
   * Ställer in GraphNodens nyckel
   * @param key Den nya nyckeln
   */
  public void setKey(int key){
    this.key = key;
  }
  
  /**
   * @return En strängrepresentation av GraphNoden
   */
  public String toString(){
    return "value: " + this.value + ", key: " + Integer.toString(this.key);
  }
  
  /**
   * Avgör om två GraphNoder är lika. Det krävs att både nyckel och värde är lika.
   * @return What do you expect?
   */
  public boolean equals(Object other) {
    if (other == null) {
      return false;
    } else if (this == other) {
      return true;
    } else if (other instanceof GraphNode) {
      GraphNode otherGraphNode = (GraphNode) other;
      return (this.getKey() == otherGraphNode.getKey() && this.getValue().equals(otherGraphNode.getValue()));
    }
    return false;
  }
  
  /**
   * Implementation av hashCode för en nod. Definieras som summan av hashkoderna för nyckel och värde
   * @return hashkod
   */
  public int hashCode() {
    return key + value.hashCode();
  }
}