import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.io.File;
import Lab3Help.*;
import java.util.Iterator;

/* Tar in hållplatslistor och tidtabeller och startar ruttsökningsprogrammet. */

public class Lab3Controller implements ActionListener {
  
  JFileChooser fileChooser;
  File stops;
  File lines;
  DijkPath pathComputer;
  Lab3Frame frame;
  BusMapFrame map;
  String origin;
  String destination;
  
  /* Startar programmet genom att be användaren välja filer med linjetabeller och hållplatser. */
  public Lab3Controller() {
    fileChooser = new JFileChooser(".");
    chooseFiles();
    this.pathComputer = new DijkPath(stops.getAbsolutePath(), lines.getAbsolutePath());
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
      System.err.println("No stops file was chosen.");
      System.exit(1);
    }
    
    /* Information till användaren */
    JOptionPane.showMessageDialog(null, "Please choose a file containing a line table.");
    
    /* Välj sedan linjefilen */
    response = this.fileChooser.showOpenDialog(null);
    if (response == JFileChooser.APPROVE_OPTION) {
      this.lines = fileChooser.getSelectedFile();
    } else {
      System.err.println("No lines file was chosen.");
      System.exit(1);
    }
  }
  
  /* Bygger vyn, lägger till sig själv som ActionListener på frame. */
  private void buildView() {
    this.frame = new Lab3Frame();
    this.frame.addActionListener(this);
    buildFrame();
    this.map = new BusMapFrame();
    this.frame.pack();
    this.frame.setVisible(true);
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
  }
  
  /* Bygger kartan genom att rita alla bågar och hållplatser samt genom att rita den slutgiltiga vägen med rött. */
  private void buildMap() {
    Iterator<GraphNode> i = pathComputer.getNodes();
    GraphNode current;
    BStop stop;
    BStop destination;
    
    this.map.initMap();
    
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
    pathComputer.computePath(this.origin, this.destination);
    map.initShortestPath();
    Iterator<Node<GraphNode>> j = pathComputer.getPath();
    BStop currentStop = j.next().getValue().getStop();
    BStop nextStop;
    
    while (j.hasNext()) {
      nextStop = j.next().getValue().getStop();
      map.drawEdge(currentStop.getX(), currentStop.getY(), nextStop.getX(), nextStop.getY());
      currentStop = nextStop;
    }
    
    /* Visar kartan */
    map.finalMap();
  }
  
  public void actionPerformed(ActionEvent e) {
    this.origin       = (String) frame.getFrom();
    this.destination  = (String) frame.getTo();
    buildMap();
    writePathInfo();
  }
  
  /* Skriver ut information om rutten som räknades ut. */
  private void writePathInfo() {
    Node<GraphNode> cursor;
    
    int totalPathLength = pathComputer.getPathLength();
    String heading = "Calculated path from " + this.origin + " to " + this.destination + ".\n";
    heading += "Total time: " + totalPathLength;
    
    this.frame.writeln(heading);
    
    Iterator<Node<GraphNode>> k = pathComputer.getPath();
    String cursorStop;
    String previousStop;
    String output;
    
    if(k.hasNext()) {
      cursor = k.next();
      this.frame.writeln("Start at " + cursor.getValue().getStop().getName());
    }    
    while(k.hasNext()) {
      cursor = k.next();
      cursorStop = cursor.getValue().getStop().getName();
      previousStop = cursor.getPrevious().getValue().getStop().getName();
      output = "Take line " + cursor.getLine() + " from " + previousStop + " to " + cursorStop + " - time: " + cursor.getTimeFromPrevious();
      this.frame.writeln(output);
    }
  }


  /* För att testa klassen. */
  public static void main(String[] args) {
    Lab3Controller c = new Lab3Controller();
  }
}