import Lab3Help.*;

/* En nod i en path. Inkapslar en busshållsplats samt linjen som används för att komma dit. */

public class PathNode {
  private BStop stop;
  private int line;
  
  public PathNode(BStop stop, int line){
    this.stop = stop;
    this.line = line;
  }
  
  public BStop getStop() {
    return this.stop;
  }
  
  public int getLine() {
    return this.line;
  }
  
  public String toString() {
    return this.stop.getName() + " med linje " + this.line;
  }
}