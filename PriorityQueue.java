import java.util.*;

/**
 * En generisk uppdaterbar prioritetskö.
 */

public class PriorityQueue<T> {
  
  /** Anges för att få fallande köordning (största elementet har högst prioritet). */
  public static final boolean DESC = false;
  /** Anges för att få stigande prioritet (elementet med lägst värde på nyckeln har högst prioritet). */
  public static final boolean ASC = true;
  
  private GenAHeap<T> heap;
  
  /**
  * @param desc Anger huruvida PriorityQueue ska vara fallande eller stigande.
  */
  public PriorityQueue(boolean desc){
    this.heap = new GenAHeap<T>(new HeapComparator(desc));
  }
  
  /**
  * Hämtar första värdet i kön.
  * Värdet plockas sedan bort.
  * @return den nod som ligger först på heapen
  */
  public Node<T> pull(){
    return this.heap.pull();
  }
  
  /**
  * Plockar bort minsta värdet på heapen
  */
  public void removeMin() {
    this.heap.removeMin();
  }
  
  /**
  * Lägger till ett värde med angiven nyckel i kön.
  * @param value Nyttovärdet som ska placeras i kön
  * @param key Nyckelvärdet
  */
  public void add(T value, int key){
    this.heap.add(new Node<T>(value,key));
  }
  
  public void add(Node<T> n) {
    this.heap.add(n);
  }
  
  /**
  * Uppdaterar ett befintligt värde i kön.
  * @param value Nyttovärdet som ska uppdateras
  * @param oldKey Föregående nyckel
  * @param newKey Nyttovärdets nya nyckel
  */
  public void update(T value, int oldKey, int newKey){
    this.heap.update(new Node<T>(value, oldKey), newKey);
  }
  
  /**
   * Uppdaterar en befintlig nod i kön.
   * @param node Noden som ska uppdateras.
   * @param newKey Den nya nyckeln.
   */
  public void update(Node<T> node, int newKey) {
    this.heap.update(node,newKey);
  }
  
  /**
  * Returnerar antalet noder i kön.
  * @return Antalet noder i kön
  */
  public int getSize(){
    return this.heap.getSize();
  }
  
  /** 
   * @return Strängrepresentation av kön
   */
  public String toString() {
    return this.heap.toString();
  }
  
  /**
   * Hämtar, men tar inte bort första noden från kön
   * @return Första värdet på heapen
   */
  public Node<T> peek() {
    return this.heap.peek();
  }
  
  /**
  * Kontrollerar huruvida kön är tom eller ej
  * @return Sant om kön är tom
  */
  public boolean isEmpty() {
    return this.heap.isEmpty();
  }
  
  /*
   * HeapComparator accepterar ett argument som bestämmer huruvida elementen ordnas i stigande eller i fallande ordning.
   */
  private class HeapComparator implements Comparator<Integer> {
    private boolean desc;
    public HeapComparator(boolean desc) {
      this.desc = desc;
    }
    public int compare(Integer a, Integer b) throws ClassCastException {
      int value = a.compareTo(b);
      return this.desc ? value : value*(-1);
    }
  }
  
}