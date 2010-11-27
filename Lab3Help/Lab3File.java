package Lab3Help;

import java.io.*;
import java.util.*;

public class Lab3File {

    /** Helper method that reads a string from the given StreamTokenizer.
     *   The method terminates the program with an error if the token read 
     *   was not a string.
     *
     *  @param st   the StreamTokenizer
     *  @return     the read string
     */

    protected String getString(StreamTokenizer st) throws IOException {
	int ttype = st.nextToken();
	
	if (ttype != StreamTokenizer.TT_WORD) {
	    throw new RuntimeException("String expected");
	}
	
	return st.sval;
    }

    /** Helper method that reads an int from the given StreamTokenizer.
     *   The method terminates the program with an error if the token read 
     *   was not an int.
     *
     *  @param st   the StreamTokenizer
     *  @return     the read int 
     */

    protected int getInt(StreamTokenizer st) throws IOException {
	int ttype = st.nextToken();
	
	if (ttype != StreamTokenizer.TT_NUMBER) {
	    throw new RuntimeException("Number expected");
	}
	
	return (int) st.nval;
    }

    /** Reads the bus stops in the given file into a Vector. Each bus stop
     *   is represented by the BStop class.
     *
     *  @param fname   the name of the file containing the bus stop data
     *  @return        a Vector of BStop
     *
     *  @see BStop
     */

    public Vector readStops(String fname) {
	Vector stops = new Vector();

	try {	    
	    Reader r = new BufferedReader(new FileReader(fname));
	    StreamTokenizer st = new StreamTokenizer(r);

	    while (st.nextToken() != StreamTokenizer.TT_EOF) {
		st.pushBack();
		String name = getString(st);
		int x       = getInt(st);
		int y       = getInt(st);
		
		stops.add(new BStop(name, x, y));
	    }
	} catch (IOException e){
	    throw new RuntimeException(e);
	}

	return stops;
    }

    
    /** Reads the bus line information from the given file returning a
     *   Vector of BLineTable. A BLineTable is a time table for a
     *   specific line. It contains an array of BLineStop,
     *   representing the list of bus stops the line stops at.
     *
     *  @param fname   the name of the file containing the bus stop data
     *  @return        a Vector of BLineTable, one BLineTable for each line
     *
     *  @see BLineTable
     *  @see BLine */


    public Vector readLines(String fname) {
	Vector res = new Vector();

	try {	
	    Reader r = new BufferedReader(new FileReader(fname));
	    StreamTokenizer st = new StreamTokenizer(r);

	    while (st.nextToken() != StreamTokenizer.TT_EOF) {
		st.pushBack();
		
		int lineNr  = getInt(st);
		int stopCnt = getInt(st);
		
		BLineStop[] stops = new BLineStop[stopCnt];
		
		String firstname = getString(st);
		stops[0] = new BLineStop(firstname, 0);
		
		for (int i = 1; i < stopCnt; i++) {
		    String name = getString(st);
		    int    time = getInt(st);
		    
		    stops[i] = new BLineStop(name, time);
		}
	    
		res.add(new BLineTable(stops, lineNr));
	    }
	} catch (IOException e){
	    throw new RuntimeException(e);
	}
	
	return res;	    
    }

}
