import junit.framework.*;
import Lab3Help.BStop;
import java.util.ArrayList;

class TestGraph extends TestCase {
  public void testGraph(){
    Graph g = new Graph();
    for(int i = 0; i<5; i++) {
      g.addNode(new GraphNode(new BStop("Apa " + i, i, i + 1)));
    }
    ArrayList<GraphNode> l = g.getNodes();
    for (int j = 0; j<l.size()-1;j++) {
      l.get(j).addEdge(new Edge(l.get(j+1), j+1, j+2));
    }
    System.out.println(g);
  }
  
  public static void main(String[] args) {
    TestGraph t = new TestGraph();
    t.testGraph();
  }
}