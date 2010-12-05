import Lab3Help.BStop;
import Lab3Help.BusMapFrame;
import java.util.Iterator;

public class Main {
  public static void main(String[] args) {
    DijkPath dp = new DijkPath("stops", "lines");
    BusMapFrame map = new BusMapFrame();
    map.initMap();
    
    /* Ritar alla hållplatser och rutter */
    Iterator<GraphNode> i = dp.getNodes();
    GraphNode current;
    BStop stop;
    BStop destination;
    
    while (i.hasNext()) {
      current = i.next();
      stop = current.getStop();
      map.drawStop(stop.getX(), stop.getY(), stop.getName());
      for (Edge e : current.getEdges()) {
        destination = e.getDestination().getStop();
        map.drawEdge(stop.getX(), stop.getY(), destination.getX(), destination.getY());
        map.nextColor();
      }
    }
    
    /* Ritar den kortaste vägen */
    dp.computePath("MIA", "ORD");
    map.initShortestPath();
    Iterator<PathNode> j = dp.getPath();
    BStop currentStop = j.next().getStop();
    BStop nextStop;
    while (j.hasNext()) {
      nextStop = j.next().getStop();
      map.drawEdge(currentStop.getX(), currentStop.getY(), nextStop.getX(), nextStop.getY());
      currentStop = nextStop;
    }
    map.finalMap();
  }
}