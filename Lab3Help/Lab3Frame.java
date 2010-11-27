package Lab3Help;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/** The Lab3Frame class is intended to give an simple graphical
 *  interface to the third laboration. The interface consists of two
 *  comboboxes (the source and destionation), a button that indicates
 *  when to calculate the shortest path and a text area, in which
 *  information from the lab implemetation can be written.
 *
 *  A typical laboration adds all nodes to the frame using the addStop
 *  method and registers itself as an ActionListener using the
 *  addActionListener method. When the button is pressed the action
 *  listener is invoked, which should get the source and destination
 *  using the getFrom and getTo methods. After having calculated the
 *  shortest path, a textual representation of the path can be written
 *  to the text area using the write or writeln methods and draw the
 *  graph using the given BusMapFrame.
 *
 */

public class Lab3Frame extends JFrame {


    protected JButton spath = new JButton("SPath");

    protected JComboBox from;
    protected JComboBox to;

    protected JTextArea tArea = new JTextArea("", 12, 30);

    protected int textPos = 0;

    /** Creates a new Lab3Fram
     *
     */

    public Lab3Frame() {
	
	String[] initialCBox = { };

	GridBagConstraints gbc = new GridBagConstraints();

	JPanel p2 = new JPanel(new GridBagLayout());
	
	gbc.insets = new Insets(2,2,2,2);

	gbc.gridx = 0; gbc.gridy = 0; 
	JLabel fromLabel = new JLabel("from:");
	from = new JComboBox(initialCBox);
	p2.add(fromLabel,gbc); gbc.gridx = 1;
	p2.add(from, gbc);

	gbc.gridx = 0; gbc.gridy = 1; 
	JLabel toLabel = new JLabel("to:");
	to   = new JComboBox(initialCBox);
	p2.add(toLabel, gbc); gbc.gridx = 1;
	p2.add(to, gbc);
	
	Container c = getContentPane();

	JPanel p1 = new JPanel(new GridBagLayout());
	
	gbc.gridx = 0; gbc.gridy = 0; 
	gbc.fill = GridBagConstraints.HORIZONTAL; 
	p1.add(p2, gbc);

	gbc.fill = GridBagConstraints.NONE; 
	gbc.gridx = 0; gbc.gridy = 1; 
	p1.add(spath, gbc);

	gbc.gridx = 1; gbc.gridy = 0; gbc.gridwidth = 0;
	gbc.gridheight = 2;

	tArea.setEditable(false);

	JScrollPane sp = new JScrollPane(tArea);
	p1.add(sp, gbc);

	c.add(p1);
    }


    /** Returns the object selected by the from combobox.
     *
     *  @return the object selected by the from combobox
     */

    public Object getFrom() {
	return from.getSelectedItem();
    }

    /** Returns the object selected by the to combobox.
     *
     *  @return the object selected by the to combobox
     */


    public Object getTo() {
	return to.getSelectedItem();
    }

    /** Writes the given string to the text area
     * 
     *  @param s    the string to write in the text area
     */


    public void write(String s) {
	tArea.insert(s, textPos);
	textPos += s.length();
    }

    /** Writes the given string to the text area appending a newline
     *
     *  @param s    the string to write in the text area
     */

    
    public void writeln(String s) {
	write(s);
	write("\n");
    }

    /** Adds a stop to both comboboxes.
     *
     *  @param o  the object to add to both comboboxes.
     */

    
    public void addStop(Object o) {
	from.addItem(o);
	to.addItem(o);
    }

    /** Adds the given ActionListener to the button of the frame.
     *
     *  @param l   the listener to be added to the button.
     */

    
    public void addActionListener(ActionListener l) {
	spath.addActionListener(l);
    }

}
