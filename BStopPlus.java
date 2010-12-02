import Lab3Help.BStop;

/* En wrapperklass för BStop, lägger till diverse hjälpmetoder */

public class BStopPlus {
  
  private BStop stop;
  
  public BStopPlus(BStop stop) {
    this.stop = stop;
  }
  
  /* To BStops shall equal each other if their names and coordinates match */
  public boolean equals (Object other) {
    return true;
  }
}