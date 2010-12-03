import java.util.*;

public class PriorityQueue<T> {
  public static final boolean DESC = false;
  public static final boolean ASC = true;
  
  private GenAHeap<T> heap;
  
  /**
  * @param desc Anger huruvida PriorityQueue ska vara fallande eller stigande
  */
  public PriorityQueue(boolean desc){
    this.heap = new GenAHeap<T>(new HeapComparator(desc));
  }
  
  /**
  * Hämtar första värdet i kön
  * Värdet plockas sedan bort
  * @return en Node som ligger första på heapen
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
  * Lägger till ett värde med angiven nyckel i kön
  * @param name Namnet, i vårt fall, på personen som ska placeras i kön
  * @param key Värdet på personen, i vårt fall värdet på aktien som personen har köpt eller sålt
  */
  public void add(T value, int key){
    this.heap.add(new Node<T>(value,key));
  }
  
  public void add(Node<T> n) {
    this.heap.add(n);
  }
  
  /**
  * Uppdaterar ett befintligt värde i kön
  * @param name Namnet på personen som ska ändras
  * @param oldKey Föregående nyckel, alltså samma nyckel som angavs i {add()}
  * @param newKey Användarens nya nyckel
  */
  public void update(T value, int oldKey, int newKey){
    this.heap.update(new Node<T>(value, oldKey), newKey);
  }
  
  public void update(Node<T> node, int newKey) {
    this.heap.update(node,newKey);
  }
  
  /**
  * Räknar ut antalet personer i kön
  * @return Antalet personer i kön
  */
  public int getSize(){
    return this.heap.getSize();
  }
  
  /** 
   * @return Sträng-värdet av kön
   */
  public String toString() {
    return this.heap.toString();
  }
  
  /**
   * Hämtar, men tar inte bort första värdet från kön
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