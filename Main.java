import Lab3Help.BStop;
import Lab3Help.BusMapFrame;
import java.util.Iterator;

public class Main {
  public static void main(String[] args) {
    DijkPath dp = new DijkPath("stops", "lines");
    dp.computePath("MIA", "ORD");
    BusMapFrame map = new BusMapFrame();
    map.initMap();
    Iterator<BStop> i = dp.getAllStops();
    BStop current;
    while (i.hasNext()) {
      current = i.next();
      map.drawStop(current.getX(), current.getY(), current.getName());
    }
    map.finalMap();
  }
}