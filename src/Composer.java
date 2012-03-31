import geometry.Posn;
import isdraw.*;
import colors.*;

// Final Project
// Robby Grodin
// 8903



/** Class representing a Composer */
public class Composer{
	Posn posn;
	
	Composer(Posn p){
		posn = p;
	}
	
	public void move(int dx, int dy){
		posn = new Posn(posn.x + dx, posn.y + dy);
	}
	
	public void draw(Canvas c){
		
		//data for corner one of cursor (top left)
		Posn p1 = new Posn(posn.x - 3, posn.y - 3);
		Posn p2 = new Posn(posn.x - 18, posn.y - 3);
		Posn p3 = new Posn(posn.x - 3, posn.y - 18);
		
		//data for corner two of cursors (top right)
		Posn p4 = new Posn(posn.x + 3, posn.y - 3);
		Posn p5 = new Posn(posn.x + 9, posn.y - 3);
		Posn p6 = new Posn(posn.x + 3, posn.y - 9);
		
		//data for corner three of cursor (bottom left)
		Posn p7 = new Posn(posn.x - 3, posn.y + 3);
		Posn p8 = new Posn(posn.x - 18, posn.y + 3);
		Posn p9 = new Posn(posn.x - 3, posn.y + 18);
		
		//data for corner four of cursors (bottom right)
		Posn p10 = new Posn(posn.x + 3, posn.y + 3);
		Posn p11 = new Posn(posn.x + 18, posn.y + 3);
		Posn p12 = new Posn(posn.x + 3, posn.y + 18);
		
		// top left
		c.drawLine(p1, p2, new Red());
		c.drawLine(p1, p3, new Red());
		
		//top right
		c.drawLine(p4, p5, new Red());
		c.drawLine(p4, p6, new Red());
		
		// bottom left
		c.drawLine(p7, p8, new Red());
		c.drawLine(p7, p9, new Red());
		
		// bottom right
		c.drawLine(p10, p11, new Red());
		c.drawLine(p10, p12, new Red());
	}

}
