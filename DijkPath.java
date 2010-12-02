import java.util.ArrayList;
import java.util.HashSet;

/**
* Calculates the shortest paths between nodes in a graph.
*/

public class DijkPath implements Path {
  
  /* The graph from which the paths are calculated */
  private Graph graph;
  
  /* The current path, represented by a list of BStops */
  private ArrayList<BStop> path;
  
  /* The length of the current path */
  private int length;
  
  /* A dummy list for debugging */
  private ArrayList<String> dummyList = new ArrayList<String>();
  
  /* Both constructors build a graph based on the input */
  public Path(String stopsFileName, String linesFileName) {
    
  }
  
  public Path(List<BStop> stops, List<BLineTable> lines) {
    
  }
  
  public void computePath(String from, String to) {
    /* A set to keep track of which nodes have been visited */
    private HashSet<Node> visited = new HashSet<Node>();
  }
  
  public Iterator getPath() {
    return new dummyList.iterator();;
  }
  
  public int getPathLength() {
    return 3;
  }
}