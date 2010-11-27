package Lab3Help;

/** The BLineTable class represents line information for the line with the 
 *   given line id. Each table contains an array of BLineStop.
 *
 *  @see BLineStop
 *
 */

public class BLineTable {
    
    protected int id;
    protected BLineStop[] stops;
    
    /** Creates a new BLineTable with the given id and the given stop 
     *   information.
     *
     *  @param stops    the stops of this line
     *  @param id       the id of this line
     *
     */

    public BLineTable(BLineStop[] stops, int id) {
	this.stops = stops;
	this.id    = id;
    }
    
    /** Returns the line id of this line table
     *
     *  @return the line id of this line table
     */

    public int getLineNo() {
	return id;
    }

    /** Returns the stops information this line table
     *
     *  @return the stop information of this line table
     */
    
    public BLineStop[] getStops() {
	return stops;
    }
}

