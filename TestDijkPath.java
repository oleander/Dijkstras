import junit.framework.*;
import java.util.*;
import java.io.*;

class TestDijkPath extends TestCase {
  public static void main(String[] x){
    try {
      
      DijkPath dj = new DijkPath("stops", "lines");
      dj.computePath("ORD", "LAX");
      Iterator i = dj.getPath();
      while (i.hasNext()) {
        System.out.println(i.next());
      }
      System.out.println(dj.getPathLength());
      
    } catch(Exception e){
      e.printStackTrace();
    }

  }
}