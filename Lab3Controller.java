import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import javax.swing.JFrame;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.io.File;
import Lab3Help.*;
import java.util.Iterator;

/**
 * Central kontrollklass för Lab3.
 * Ber användaren att tillhandahålla filer med hållplatser och linjetabeller.
 * Instansierar DijkPath samt fönster för GUI.
 * Lyssnar på events från GUI:t.
 * Beräknar vid begäran den kortaste vägen samt ritar upp linjenät och kortaste vägen i en karta.
 * Skriver även ut ruttinformation i textfönstret.
 */

public class Lab3Controller implements ActionListener {
  
  private final boolean LEFT = true;
  private final boolean RIGHT = false;
  
  private final String frameWindowTitle = "UltraPathSearchEngine";
  private final String mapWindowTitle   = "UltraPathSearchEngine - Mapalyze";
  
  private JFileChooser fileChooser;
  private File stops;
  private File lines;
  private DijkPath pathComputer;
  private Lab3Frame frame;
  private BusMapFrame map;
  private String origin;
  private String destination;
  
  /** Startar programmet genom att be användaren välja filer med linjetabeller och hållplatser. */
  public Lab3Controller() {
    fileChooser = new JFileChooser(".");
    chooseFiles();
    try {
      this.pathComputer = new DijkPath(stops.getAbsolutePath(), lines.getAbsolutePath());
    } catch (GeneralException e) {
      JOptionPane.showMessageDialog(null, e.getMessage());
      System.exit(1);
    }
    buildView();
  }
  
  /* Låter användaren välja vilka hållplatser och linjer som ska användas */
  private void chooseFiles(){
    
    /* Information till användaren. */
    JOptionPane.showMessageDialog(null, "Please choose a file containing a list of stops.");
    
    /* Välj först hållplatsfilen */
    int response = this.fileChooser.showOpenDialog(null);
    if(response == JFileChooser.APPROVE_OPTION) {
      this.stops = fileChooser.getSelectedFile();
    } else {
      JOptionPane.showMessageDialog(null,"No stops file was chosen.");
      System.exit(1);
    }
    
    /* Information till användaren */
    JOptionPane.showMessageDialog(null, "Please choose a file containing a line table.");
    
    /* Välj sedan linjefilen */
    response = this.fileChooser.showOpenDialog(null);
    if (response == JFileChooser.APPROVE_OPTION) {
      this.lines = fileChooser.getSelectedFile();
    } else {
      JOptionPane.showMessageDialog(null,"No lines file was chosen.");
      System.exit(1);
    }
  }
  
  /* Bygger vyn, lägger till sig själv som ActionListener på frame. */
  private void buildView() {
    this.frame = new Lab3Frame();
    this.frame.addActionListener(this);
    this.frame.setTitle(this.frameWindowTitle);
    this.frame.setDefaultCloseOperation(this.frame.EXIT_ON_CLOSE);
    buildFrame();
  }
  
  /* Lägger till alla hållplatser i hållplatslistan i frame */
  private void buildFrame() {
    Iterator<GraphNode> i = pathComputer.getNodes();
    GraphNode current;
    BStop stop;
    
    while(i.hasNext()) {
      current = i.next();
      frame.addStop(current.getStop().getName());
    }
    
    this.frame.pack();
    moveFrame(this.frame, this.LEFT);
    this.frame.setVisible(true);
  }
  
  /* Bygger kartan genom att rita alla bågar och hållplatser samt genom att rita den slutgiltiga vägen med rött. */
  private void buildMap() {
    Iterator<GraphNode> i = pathComputer.getNodes();
    GraphNode current;
    BStop stop;
    BStop destination;
    
    if (this.map != null) {
      this.map.dispose();
    }
    this.map = new BusMapFrame();
    
    this.map.initMap();
    this.map.setTitle(this.mapWindowTitle);
    
    /* Lägg in alla hållplatser och rutter */
    while (i.hasNext()) {
      current = i.next();
      stop = current.getStop();
      this.map.drawStop(stop.getX(), stop.getY(), stop.getName());
      for (Edge e : current.getEdges()) {
        destination = e.getDestination().getStop();
        this.map.drawEdge(stop.getX(), stop.getY(), destination.getX(), destination.getY());
        this.map.nextColor();
      }
    }
    
    /* Ritar den kortaste vägen */
    map.initShortestPath();
    Iterator<Node<GraphNode>> j = pathComputer.getPath();
    BStop currentStop = j.next().getValue().getStop();
    BStop nextStop;
    
    while (j.hasNext()) {
      nextStop = j.next().getValue().getStop();
      map.drawEdge(currentStop.getX(), currentStop.getY(), nextStop.getX(), nextStop.getY());
      currentStop = nextStop;
    }
    /* Flyttar kartan till lämpligt ställe på skärmen */
    moveFrame(this.map, RIGHT);
    /* Visar kartan */
    map.finalMap();
  }
  
  /* Flyttar en frame till mitten av rutan och sedan hälften av sin bredd åt vänster eller höger beroende på indata. 
   * Genom att flytta en frame åt höger och annan en åt vänster får vi dem att inte överlappa varandra.
   */
  private void moveFrame(JFrame c, boolean left) {
    c.setLocationRelativeTo(null);
    int x = c.getX();
    int newX = left ? x - 10 - (c.getWidth() / 2) : x + 10 + (c.getWidth() / 2);
    c.setLocation(newX, c.getY());
  }
  
  /** Fångar klick från GUI. Sparar undan sökta noder, utför sökning och bygger kartan. */
  public void actionPerformed(ActionEvent e) {
    this.origin       = (String) frame.getFrom();
    this.destination  = (String) frame.getTo();
    try {
      pathComputer.computePath(this.origin, this.destination);
    } catch (GeneralException ge) {
      JOptionPane.showMessageDialog(null, ge.getMessage());
    }
    buildMap();
    writePathInfo();
  }
  
  /* Skriver ut information om rutten som räknades ut. */
  private void writePathInfo() {
    Node<GraphNode> cursor;
    
    /* Skriver först ut information om hela sträckan */
    int totalPathLength = pathComputer.getPathLength();
    String heading = "Calculated path from " + this.origin + " to " + this.destination + ".\n";
    heading += "Total time: " + totalPathLength;
    
    this.frame.writeln(heading);
    
    /* Skriver sedan ut information om starthållplatsen */
    Iterator<Node<GraphNode>> k = pathComputer.getPath();
    String cursorStop;
    String previousStop;
    String output;
    
    if(k.hasNext()) {
      cursor = k.next();
      this.frame.writeln("Start at " + cursor.getValue().getStop().getName());
    }
    
    /* Skriver till sist ut information om varje delsträcka. */
    while(k.hasNext()) {
      cursor = k.next();
      cursorStop = cursor.getValue().getStop().getName();
      previousStop = cursor.getPrevious().getValue().getStop().getName();
      output = "Take line " + cursor.getLine() + " from " + previousStop + " to " + cursorStop + " - time: " + cursor.getTimeFromPrevious();
      this.frame.writeln(output);
    }
    
    /* Skriver ut hur länge beräkningen tog. */
    this.frame.writeln("Calculation took " + pathComputer.getExecutionTime() + " milliseconds.");
    /* Avslutar med tom rad. */
    this.frame.writeln("");
  }


  /* Bootar programmet. */
  public static void main(String[] args) {
    Lab3Controller c = new Lab3Controller();
  }
}