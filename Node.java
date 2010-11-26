/**
 * En nod i en heap.
 */

public class Node {
  private int key;
  private String value;
  
  /**
   * @param value Nyttovärdet i noden
   * @param key Nyckeln som används för att prioritera noden
   */
  public Node(String value, int key){
    this.value = value;
    this.key = key;
  }
  
  /**
   * @return Nodens nyckel
   */
  public int getKey(){
    return this.key;
  }
  
  /**
   * @return Nodens nyttovärde
   */
  public String getValue(){
    return this.value;
  }
  
  /**
   * Ställer in nodens nyttovärde
   * @param value Det nya värdet
   */
  public void setValue(String value){
    this.value = value;
  }
  
  /**
   * Ställer in nodens nyckel
   * @param key Den nya nyckeln
   */
  public void setKey(int key){
    this.key = key;
  }
  
  /**
   * @return En strängrepresentation av noden
   */
  public String toString(){
    return "value: " + this.value + ", key: " + Integer.toString(this.key);
  }
  
  /**
   * Avgör om två noder är lika. Det krävs att både nyckel och värde är lika.
   * @return What do you expect?
   */
  public boolean equals(Object other) {
    if (other == null) {
      return false;
    } else if (this == other) {
      return true;
    } else if (other instanceof Node) {
      Node otherNode = (Node) other;
      return (this.getKey() == otherNode.getKey() && this.getValue().equals(otherNode.getValue()));
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