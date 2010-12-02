import junit.framework.*;
import java.util.*;

public class TestEdge extends TestCase {
  
  public void testConstructor() {
    String name = "Testnode";
    Node<String> destination = new Node<String>(name,1);
    Edge e = new Edge(destination,2,3);
    assertEquals(name, e.getDestination().getValue());
    assertEquals(2,e.getWeight());
    assertEquals(3,e.getLine());
  }
  
  
  public static void main(String[] args) {
    TestEdge t = new TestEdge();
    t.testConstructor();
  }
  
}