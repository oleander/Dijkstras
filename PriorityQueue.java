import java.util.*;

public class PriorityQueue {
  public static final boolean DESC = false;
  public static final boolean ASC = true;
  
  private AHeap heap;
  
  /**
  * @param desc Anger hurvida PriorityQueue ska vara fallande eller stigande
  */
  public PriorityQueue(boolean desc){
    this.heap = new AHeap(new HeapComparator(desc));
  }
  
  /**
  * Hämtar första värdet i kön
  * Värdet plockas sedan bort
  * @return en Node som ligger första på heapen
  */
  public Node pull(){
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
  public void add(String name, int key){
    this.heap.add(new Node(name,key));
  }
  
  /**
  * Uppdaterar ett befintligt värde i kön
  * @param name Namnet på personen som ska ändras
  * @param oldKey Föregående nyckel, alltså samma nyckel som angavs i {add()}
  * @param newKey Användarens nya nyckel
  */
  public void update(String name, int oldKey, int newKey){
    this.heap.update(new Node(name,oldKey), newKey);
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
  public Node peek() {
    return this.heap.peek();
  }
  
  /**
  * Kontrollerar hurvida kön är tom eller ej
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