package Lab3Help;

/**
 * Operations to draw a map in window of size 1000 * 1000, 
 * (0,0) is lower/left.
 */
interface BusMapInf {
    /**
     * Initiate
     */
    public void initMap();
    /**
     * Start drawing red and thick
     */
    public void initShortestPath();
    /**
     * Start drawing in black and then go round all the defined
     * colors: Black, Blue, Green, White, Yellow, Violet, Orange
     */
    public void nextColor();
    /** 
     * Draw a line between the points
     */
    public void drawEdge(int fromX, int fromY, int toX, int toY);
    /** 
     * Draw a filled circle and print the name.
     */
    public void drawStop(int x, int y, String name);
    /**
     * Show the map.
     */
    public void finalMap();
}

