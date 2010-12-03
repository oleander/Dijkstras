import java.util.ArrayList;
/**
 * En generisk nod i en graf.
 * Sparar ett nyttovärde och en nyckel samt lista på bågar som utgår från noden
 */

public class Node<T> {
  
  private int key;
  private T value;
  private Node previous;
  
  /* Linjen man använder för att komma från förra noden */
  private int lineToPrevious;
  
  /**
   * @param value Nyttovärdet i Noden
   * @param key Nyckeln som används för att prioritera Noden
   */
  public Node(T value){
    this(value, Integer.MAX_VALUE);
  }
  
  public Node(T value, int key){
    this.value = value;
    this.key = key;
  }
  
  public Node getPrevious(){
    return this.previous;
  }
  
  public void setPrevious(Node n){
    this.previous = n;
  }
  
  public void setLine(int line) {
    this.lineToPrevious = line;
  }
  
  public int getLine() {
    return this.lineToPrevious;
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
  public T getValue(){
    return this.value;
  }
  
  /**
   * Ställer in Nodens nyttovärde
   * @param value Det nya värdet
   */
  public void setValue(T value){
    this.value = value;
  }
  
  /**
   * Ställer in Nodens nyckel
   * @param key Den nya nyckeln
   */
  public void setKey(int key){
    this.key = key;
  }
  
  /**
   * @return En strängrepresentation av Noden
   */
  public String toString(){
    return "BEGIN NODE =======>\nvalue: \n" + this.value + "\nkey: " + Integer.toString(this.key) + "\n<===== END NODE";
  }
  
  /**
   * Avgör om två Noder är lika. Det krävs att både nyckel och värde är lika.
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