import java.util.ArrayList;
/**
 * En nod i en prioritetskö i en DijkPath.
 * Inkapslar en GraphNode och innehåller dessutom data om hur man tagit sig till noden samt hur länge det tagit.
 */

public class Node<T> {
  
  private int key;
  private T value;
  private Node previous;
  
  /* Linjen man använder för att komma från förra noden */
  private int lineToPrevious;
  private int timeFromPrevious;
  
  /**
   * Konstruerar en nod med maximalt värde på nyckeln.
   * @param value Nyttovärdet i Noden
   */
  public Node(T value){
    this(value, Integer.MAX_VALUE);
  }
  
  /**
   * Konstruerar en nod med värdet och nyckeln som specificerats.
   * @param value Nyttovärdet i Noden
   * @param key Nyckeln som används för att prioritera Noden
   */
  public Node(T value, int key){
    this.value = value;
    this.key = key;
  }
  
  /**
   * @return Noden som ligger före den aktuella noden i den kortaste vägen.
   */
  public Node<T> getPrevious(){
    return this.previous;
  }
  
  /**
   * Specificerar vilken nod som kommer före den aktuella noden i den kortaste vägen.
   * @param n Noden som kommer före den aktuella noden i den kortaste vägen.
   */
  public void setPrevious(Node n){
    this.previous = n;
  }
  
  /**
   * Specificera hur länge det tar att ta sig till den aktuella noden från noden före i den kortaste vägen.
   * @param time Tiden mellan noderna
   */
  public void setTimeFromPrevious(int time) {
    this.timeFromPrevious = time;
  }
  
  /**
   * @return Hur länge det tog att ta sig till den aktuella noden från den föregående noden.
   */
  public int getTimeFromPrevious() {
    return this.timeFromPrevious;
  }
  
  /**
   * Specificera vilken linje som används för att ta sig snabbast från föregående nod till den aktuella noden.
   * @param line Linjen som används
   */
  public void setLine(int line) {
    this.lineToPrevious = line;
  }
  
  /**
   * @return Linjen som man tagit sig till noden med.
   */
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