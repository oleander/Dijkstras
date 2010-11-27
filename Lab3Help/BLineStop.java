package Lab3Help;

/** A BLineStop represents a bus stop visited by a line. Each line has
 * an associated BLineTable, containing an array of BLineStops. Each
 * BLineStop contains the name of the stop and the amount of time
 * needed for the transition from the stop before to the stop after.
 *
 */

public class BLineStop {
    
    protected String stop;
    protected int time;
   
    /** Creates a new BLineStop with the given name and the given
     *  transition time.
     * 
     *  @param stop   the name of the stop
     *  @param time   the transition time from the stop before to this
     *                 stop.
     */
 
    public BLineStop(String stop, int time) {
	this.stop = stop;
	this.time = time;
    }
    
    /** Returns the time needed from the stop before to this stop.
     *
     *  @return   the time needed from the stop before to this stop.
     *
     */

    public int getTime() {
	return time;
    }

    /** Returns the name of this stop.
     *
     *  @return   the name of this stop.
     *
     */
    
    public String getName() {
	return stop;
    }
}

