import colors.*;
import geometry.Posn;
import isdraw.*;


//Final Project
//Robby Grodin
//8903



/** Class representing a MIDI event */
public class Note{
	Posn pos;
	IColor col;
	String shape;
	int size = 10;
	
	/** Create a new instance of <code> Note </code>
	 * @param p Posn = position of the note on the <code>Stage</code>
	 * @param i int = the instrument that the note is being added to
	 */
	Note(Posn p, int i){
		pos = p;
		
		// sets the color field
		if(i == 1){
			col = new Red();
			shape = "rect";
		}
		if(i == 2){
			col = new Green();
			shape = "tri";
		}
		if(i == 3){
			col = new Blue();
			shape = "circ";
		}
		if(i == 4){
			col = new Yellow();
			shape = "disc";
		}
	}
	
	/**
	 * Draw the <code> Note </code> on the given canvas
	 * @param c Canvas = the canvas on which to draw the <code> Note </code>
	 */
	public void draw(Canvas c){
		if(shape == "rect")
			c.drawRect(this.pos, size, size, col);
		if(shape == "circ")
			c.drawRect(this.pos, size, size, col);
		if(shape == "tri")
			c.drawRect(this.pos, size, size, col);
		if(shape == "disc")
			c.drawRect(this.pos, size, size, col);
	}
}
