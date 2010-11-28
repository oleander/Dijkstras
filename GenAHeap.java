/** En generisk klass för uppdaterbar heap.
 *  @author Jesper Josefsson
 *  @author Linus Oleander
 */

import java.util.*;

public class GenAHeap<T> {  
  private Comparator comparator;
  private ArrayList<Node<T>> list;
  private int size;
  private HashMap<Node<T>,Integer> positionMap;
  
  /**
   * @param comparator komparatorn som bestämmer vilket element är högst prioriterat
   */
  public GenAHeap(Comparator comparator){
    this.comparator = comparator;
    this.size = 0;
    this.list = new ArrayList<Node<T>>();
    this.list.add(new Node<T>(null, 0));
    this.positionMap = new HashMap<Node<T>,Integer>();
  }
  
  /**
   * Gör det möjligt att lägga till noder i heapen
   * @param n Noden som ska läggas till i kön
   */
  public void add(Node<T> n){
    size++;
    if(this.list.size() <= size){
     this.list.add(n);
    } else {
      list.set(size, n);
    }
    positionMap.put(n,size);
    bubbleUp(n);
  }
  
  /**
   * Storleken på heap
   * @return int Storleken på heapen, där en tom heap har värdet 0 
   */
  public int getSize(){
    return size;
  }
  
  /**
  * Kontrollerar hurvida heapen är tom eller ej
  * @return True om heapen är tom
  */
  public boolean isEmpty() {
    return size == 0;
  }
  
  /**
  * Hämtar första värden i heapen
  * Är heapen tom så retunerar {null}
  * Plockar även bort värdet från heapen
  * @return Första värdet i heapen
  */
  public Node<T> pull(){
    if(this.size == 0) return null;
    
    Node<T> output = get(1);
    this.delete(1);
    return output;
  }
  
  /**
  * Hämtar Noden på plats {index} i heapen
  * Plockar sedan bort värdet från heapen
  * @param index Anger vilken nod som ska retunerars av heapen där 0 är första Noden
  * @return Noden som finns på plats {index}, finns inte Noden så kastas ett fel
  */
  public Node<T> get(int index){
    if (index <= size && index > 0) {
      return this.list.get(index);
    } else {
      throw new IndexOutOfBoundsException("Error in: get");
    }
  }
  
  /**
   * Hämtar, men tar inte bort första värdet från heapen
   * @return Första värdet på heapen
   */
  public Node<T> peek() {
    if (this.size == 0) {
      throw new GeneralException("Heap empty");
    }
    return this.get(1);
  }

  /** 
   * Uppdaterar nyckeln på en nod 
   * @param old Noden som skall förändras
   * @param key Den nya nyckeln
   */

  public void update(Node<T> old, int key) throws GeneralException {
    if (positionMap.get(old) == null) {
      throw new GeneralException("Error in update: Node<T> not found!");
    } else {
    int index = positionMap.get(old);
    this.positionMap.remove(old);
    this.list.get(index).setKey(key);
    this.positionMap.put(this.list.get(index),index);
    this.bubble(list.get(index));
    }
  }
  
  /* Plockar bort Noden på plats {index}
  */
  private void delete(int index){
    /* Kopierar den sista Noden till det givna indexet */
    Node<T> lastNode = list.get(size);
    list.set(index, lastNode);
    
    /* Sparar undan den flyttade Nodens index i positionsmappen */
    positionMap.put(lastNode,index);
    size--;
    
    /* Ser till att den flyttade Noden ligger på rätt ställe */
    bubbleDown(lastNode);
    bubbleUp(lastNode);
  }
  
  /** 
   * Tar bort högst prioriterat element utan att returnera det 
   */
  public void removeMin(){
    this.delete(1);
  }
  
  /* Byter plats på två Node<T>r */
  private void swap(Node<T> a, Node<T> b){
    /* Tar fram index för vardera nod */
    int indexA = positionMap.get(a);
    int indexB = positionMap.get(b);
    
    /* sparar ner Node<T>rna på sina nya positioner */
    list.set(indexB, a);
    list.set(indexA, b);
    
    /* uppdaterar positionsmappen */
    positionMap.put(a,indexB);
    positionMap.put(b,indexA);
  }
  
  private Node<T> getParent(Node<T> n) {
    int index = positionMap.get(n);
    return list.get(index/2);
  }
  
  /* Utför både nedåt- och uppåtbubbling */
  private void bubble(Node<T> n) {
    this.bubbleUp(n);
    this.bubbleDown(n);
  }
  
  /* Flyttar en nod uppåt i heapen till rätt position */
  private void bubbleUp(Node<T> n) {
    /* Om n är root avbryter vi */
    if (list.get(1).equals(n)) {
      return;
    }
    
    /* Annars tar vi fram n:s förälder och kollar heapvillkor
     * Om villkoret ej uppfylls swappar vi och kör bubbleUp en gång till */
    Node<T> parent = this.getParent(n);
    if (compareNodes(parent,n) > 0) {
      swap(parent,n);
      bubbleUp(n);
    }
  }
  
  /* Ser till att en nod är på rätt ställe i trädet genom att flytta den nedåt i trädet */
  private void bubbleDown(Node<T> n) {
    if (!hasChildren(n)) return;
    
    int thisKey = n.getKey();
    int leftChildKey = leftChild(n).getKey();
    
    /* Om noden inte har ett högerbarn kollar vi om vänsterbarnet uppfyller heapvillkoret */
    if (!hasRightChild(n)) {
      /* Om villkoret inte uppfylls swappar vi och bubblar en gång till, annars vet vi att vi är klara */
      if (comparator.compare(leftChildKey, thisKey) < 0) {
        swap(n,leftChild(n));
        bubbleDown(n);
      } else {
        return;
      }
    /* Om noden har ett högerbarn jämför vi båda barn med n */
    } else {
      int rightChildKey = rightChild(n).getKey();
      /* Om något av barnen har lägre nyckel än n ska vi swappa */
      if ((comparator.compare(leftChildKey, thisKey) < 0) || (comparator.compare(rightChildKey, thisKey) < 0)){
        Node<T> swapNode = comparator.compare(leftChildKey,rightChildKey) < 0 ? leftChild(n) : rightChild(n);
        swap(n,swapNode);
        bubbleDown(n);
      } else {
        return;
      }
    }
  }
  
  private boolean hasChildren(Node<T> n) {
    return positionMap.get(n)*2 <= size;
  }
  
  private boolean hasRightChild(Node<T> n) {
    return positionMap.get(n)*2 + 1 <= size;
  }
  
  private Node<T> leftChild(Node<T> n) {
    int index = positionMap.get(n);
    return list.get(index*2);
  }
  
  private Node<T> rightChild(Node<T> n) {
    int index = positionMap.get(n);
    return list.get(index*2 + 1);
  }
  
  /* Jämför nycklarna på två Noder */
  private int compareNodes(Node<T> a, Node<T> b) {
    return this.comparator.compare(a.getKey(),b.getKey());
  }
  
  /**
   * @return String en strängrepresentation av heapen
   */
  public String toString() {
    return this.list.toString();
  }
}