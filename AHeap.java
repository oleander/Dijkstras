/** En uppdaterarbar heap. Intar som nyckel och strängar som värde, tillsvidare.
 *  @author Jesper Josefsson
 *  @author Linus Oleander
 */

import java.util.*;

public class AHeap {  
  private Comparator comparator;
  private ArrayList<Node> list;
  private int size;
  private HashMap<Node,Integer> positionMap;
  
  /**
   * @param comparator komparatorn som bestämmer vilket element är högst prioriterat
   */
  public AHeap(Comparator comparator){
    this.comparator = comparator;
    this.size = 0;
    this.list = new ArrayList<Node>();
    this.list.add(new Node("My empty node", 0));
    this.positionMap = new HashMap<Node,Integer>();
  }
  
  /**
   * Gör det möjligt att lägga till noder i heapen
   * @param n Noden som ska läggas till i kön
   */
  public void add(Node n){
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
  public Node pull(){
    if(this.size == 0) return null;
    
    Node output = get(1);
    this.delete(1);
    return output;
  }
  
  /**
  * Hämtar Noden på plats {index} i heapen
  * Plockar sedan bort värdet från heapen
  * @param index Anger vilken nod som ska retunerars av heapen där 0 är första noden
  * @return Noden som finns på plats {index}, finns inte noden så kastas ett fel
  */
  public Node get(int index){
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
  public Node peek() {
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

  public void update(Node old, int key) throws GeneralException {
    if (positionMap.get(old) == null) {
      throw new GeneralException("Error in update: Node not found!");
    } else {
    int index = positionMap.get(old);
    this.positionMap.remove(old);
    this.list.get(index).setKey(key);
    this.positionMap.put(this.list.get(index),index);
    this.bubble(list.get(index));
    }
  }
  
  /* Plockar bort noden på plats {index}
  */
  private void delete(int index){
    /* Kopierar den sista noden till det givna indexet */
    Node lastNode = list.get(size);
    list.set(index, lastNode);
    
    /* Sparar undan den flyttade nodens index i positionsmappen */
    positionMap.put(lastNode,index);
    size--;
    
    /* Ser till att den flyttade noden ligger på rätt ställe */
    bubbleDown(lastNode);
    bubbleUp(lastNode);
  }
  
  /** 
   * Tar bort högst prioriterat element utan att returnera det 
   */
  public void removeMin(){
    this.delete(1);
  }
  
  /* Byter plats på två noder */
  private void swap(Node a, Node b){
    /* Tar fram index för vardera nod */
    int indexA = positionMap.get(a);
    int indexB = positionMap.get(b);
    
    /* sparar ner noderna på sina nya positioner */
    list.set(indexB, a);
    list.set(indexA, b);
    
    /* uppdaterar positionsmappen */
    positionMap.put(a,indexB);
    positionMap.put(b,indexA);
  }
  
  private Node getParent(Node n) {
    int index = positionMap.get(n);
    return list.get(index/2);
  }
  
  /* Utför både nedåt- och uppåtbubbling */
  private void bubble(Node n) {
    this.bubbleUp(n);
    this.bubbleDown(n);
  }
  
  /* Flyttar en nod uppåt i heapen till rätt position */
  private void bubbleUp(Node n) {
    /* Om n är root avbryter vi */
    if (list.get(1).equals(n)) {
      return;
    }
    
    /* Annars tar vi fram n:s förälder och kollar heapvillkor
     * Om villkoret ej uppfylls swappar vi och kör bubbleUp en gång till */
    Node parent = this.getParent(n);
    if (compareNodes(parent,n) > 0) {
      swap(parent,n);
      bubbleUp(n);
    }
  }
  
  /* Ser till att en nod är på rätt ställe i trädet genom att flytta den nedåt i trädet */
  private void bubbleDown(Node n) {
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
        Node swapNode = comparator.compare(leftChildKey,rightChildKey) < 0 ? leftChild(n) : rightChild(n);
        swap(n,swapNode);
        bubbleDown(n);
      } else {
        return;
      }
    }
  }
  
  private boolean hasChildren(Node n) {
    return positionMap.get(n)*2 <= size;
  }
  
  private boolean hasRightChild(Node n) {
    return positionMap.get(n)*2 + 1 <= size;
  }
  
  private Node leftChild(Node n) {
    int index = positionMap.get(n);
    return list.get(index*2);
  }
  
  private Node rightChild(Node n) {
    int index = positionMap.get(n);
    return list.get(index*2 + 1);
  }
  
  /* Jämför nycklarna på två noder */
  private int compareNodes(Node a, Node b) {
    return this.comparator.compare(a.getKey(),b.getKey());
  }
  
  /**
   * @return String en strängrepresentation av heapen
   */
  public String toString() {
    return this.list.toString();
  }
}