import java.util.ArrayList;

/**
* Calculates the shortest paths between nodes in a graph.
*/

public class DijkPath implements Path {
  
  /* The graph from which the paths are calculated */
  private Graph graph;
  
  /* The current path, represented by a list of BStops */
  private ArrayList<BStop> path;
  
  /* The length of the path */
  private int length;
  
  /* A dummy list for debugging */
  private ArrayList<String> dummyList = new ArrayList<String>();
  
  public void computePath(String from, String to) {
    
  }
  
  public Iterator getPath() {
    return new dummyList.iterator();;
  }
  
  public int getPathLength() {
    return 3;
  }
}