import junit.framework.*;
import java.util.*;
import java.io.*;

class TestDijkPath extends TestCase {
  public static void main(String[] x){
    try {
      
      DijkPath dj = new DijkPath("stops", "lines");
      dj.computePath("MIA", "JFK");
      
    } catch(Exception e){
      e.printStackTrace();
    }

  }
}