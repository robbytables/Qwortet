import java.util.ArrayList;

import isdraw.*;
import geometry.Posn;
import colors.*;
import tunes.*;


//Final Project
//Robby Grodin
//8903


/** 
 * Java Qwortet
 * A step sequencer for up to 4 performers.
 * 
 *@author Robby Grodin
 * 4.19.2010
 */

/**
 *  Class representing the MIDI Event Sequencer.
 *  Notes are arranged on a Pitch/Time graph.
 *  Notes are added by using the cursor.
 *  The higher (graphically) the note, the higher the pitch,
 *  and the further to the right it is, the later it will play (on a time scale).
 *  Keys 1-4 switch through the instruments, each represented by a specific color.
 *  Instruments can be set in the fields for the <code>Sequencer</code> class
 */
public class Sequencer extends World{
	// Declare the fields for the class
	
	
	// Initialize the Song
	Song song = new Song();
	
	// These are the four instruments.
	static int channel1 = PIANO;
	static int channel2 = CLARINET;
	static int channel3 = BIRD_TWEET; 
	static int channel4 = VIOLIN;
	
	// Names for each instrument
	String INSTRUMENT_ONE_NAME = "Piano";
	String INSTRUMENT_TWO_NAME = "Cello";
	String INSTRUMENT_THREE_NAME = "Bird Tweet";
	String INSTRUMENT_FOUR_NAME = "Violin";

	
	
	// The height and width of the Canvas
	int WIDTH = 1000;
	int HEIGHT = 800;
	
	// Global Variables to determine which instrument is currently selected
	int INSTR = 1;
	int CHANNEL = channel1;
	
	// Global timer variables
	int TUNE_LENGTH = 799;
	int COUNTER = 0;
	int TICK_COUNTER = 0; 
	
	int MEASURE = TICK_COUNTER;
	int BEAT = 0;
	
	// Global Variable to determine whether or not sequencer is playing
	boolean POWER = false;

	// List of graphic representation of notes.
	ArrayList<Note> NOTES = new ArrayList<Note>();
	
	// Current composer (cursor)
	Composer COMP; 
	
	// End of field declarations
	
	/**
	 * Creator method for the class <code> Sequencer </code>.
	 * @param c Composer = initial position of composer
	 */
	public Sequencer(Composer c){
		COMP = c;
	}
	
	/**
	 * Draw the following on the Canvas:
	 * Graph lines, NowTime, Notes, and Composer
	 */
	public void draw() {
		this.theCanvas.drawRect(new Posn(0,0), WIDTH, HEIGHT, new White());
		this.theCanvas.drawLine(new Posn(50, 50), new Posn(50, 562), new Black());
		this.theCanvas.drawLine(new Posn(50, 562), new Posn(850, 562), new Black());
		this.drawGrid();
		this.drawNowTime();
		this.drawNotes();
		this.drawPower(this.POWER);
		COMP.draw(this.theCanvas);
		
		// Outputs current position of the cursor as a string
		this.theCanvas.drawString(new Posn(830, 642), "NOTE: " + (562 - COMP.posn.y) / 4);
		this.theCanvas.drawString(new Posn(830, 654), "TIME:  " + ((COMP.posn.x - 50) / 4));
		
		// Draws the NOW time
		this.theCanvas.drawString(new Posn(50,50), MEASURE + ":");
		
		// Prints out instrument key, for user
		//INSTRUMENT ONE
		this.theCanvas.drawRect(new Posn(600, 660), 10, 10, new Red());
		this.theCanvas.drawString(new Posn(612, 672), "Instrument 1: " + this.INSTRUMENT_ONE_NAME);
		
		//INSTRUMENT TWO
		this.theCanvas.drawRect(new Posn(600, 680), 10, 10, new Green());
		this.theCanvas.drawString(new Posn(612, 692), "Instrument 2: " + this.INSTRUMENT_TWO_NAME);
		
		//INSTRUMENT THREE
		this.theCanvas.drawRect(new Posn(600, 700), 10, 10, new Blue());
		this.theCanvas.drawString(new Posn(612, 712), "Instrument 3: " + this.INSTRUMENT_THREE_NAME);
		
		//INSTRUMENT FOUR
		this.theCanvas.drawRect(new Posn(600, 720), 10, 10, new Yellow());
		this.theCanvas.drawString(new Posn(612, 732), "Instrument 4: " + this.INSTRUMENT_FOUR_NAME);
	}
	
	/**
	 * Processes key events.
	 * Arrow Keys: move cursor
	 * Space Bar: add note to sequencer
	 * 1,2,3,4: changes selected instrument variable to
	 *          red, green, blue, yellow (respectively)
	 * P: signals endOfWorld
	 * 
	 * @param ke String = identifier for key event
	 */
	public void onKeyEvent(String ke) {
		
		// flip the power switch
		if(ke.equals("t"))
			flipPower();
		
		// code to move the composer
		if(ke.equals("left"))
			if(COMP.posn.x > 50)
				this.COMP.move(-4, 0);
		
		if(ke.equals("up") 
				&& COMP.posn.y >= 54) //TODO
			
				this.COMP.move(0, -4);
		
		if (ke.equals("right")
				&& COMP.posn.x < 849)
			
				this.COMP.move(4, 0);
		
		if(ke.equals("down"))
			if(COMP.posn.y <= 558)
				this.COMP.move(0, 4);
		
		// code to add an event to the Song, and a note to the GUI
		if(ke.equals(" ")){
			song.addEvent(
					CHANNEL, 
					(562 - COMP.posn.y) / 4, 
					COMP.posn.x - 50);
			
			NOTES.add(new Note(COMP.posn, INSTR));
			
			keyTunes.addTune(new Tune(CHANNEL,new tunes.Note((562 - COMP.posn.y) / 4)));
			
			System.out.println(new Tune(CHANNEL, new tunes.Note((562 - COMP.posn.y) / 4)));
		}
		
		
		// code to change global track selector variables
		if(ke.equals("1")){
			INSTR = 1;
			CHANNEL = channel1;
		}
		if(ke.equals("2")){
			INSTR = 2;
			CHANNEL = channel2;
		}
		if(ke.equals("3")){
			INSTR = 3;
			CHANNEL = channel3;
		}
		if(ke.equals("4")){
			INSTR = 4;
			CHANNEL = channel4;
		}
		if(ke.equals("r")){
			COUNTER = 0;
			TICK_COUNTER = 0; 
		} //TODO test
		
		// Signal the endofWorld
		if(ke.equals("p"))
			endOfWorld("sequence aborted.");
	}

	/**
	 * This method handles tick events. When a tick goes by, both
	 * the COUNTER and TICK_COUNTER are calculated. 
	 * On each tick, Tunes are retrieved from the Song, and are added
	 * to the tick tuneBucket.
	 */
	public void onTick() {
		// If the power is on...
		if(POWER){
			
			// Play the notes
			tickTunes.addTunes(song.getNotes(TICK_COUNTER));
			
			// Increment the counters
			COUNTER = COUNTER + 1;
			TICK_COUNTER = (TICK_COUNTER + 1) % TUNE_LENGTH;
			
		}
		// If the power is off, do nothing.
	}
	
	/**
	 * This method is called when the user wants to play/stop
	 * the sequence. True = play, False = stop.
	 */
	public void flipPower(){
		POWER = !POWER;//TODO test
	}
	
	/**
	 * Draws the button that displays whether or not the song is playing
	 */
	public void drawPower(boolean b){
		if(b){
			this.theCanvas.drawRect(new Posn(0,650), 50, 50, new Green());
			this.theCanvas.drawLine(new Posn(10,660), new Posn(10,690), new Black());
			this.theCanvas.drawLine(new Posn(10, 660), new Posn(40, 675), new Black());
			this.theCanvas.drawLine(new Posn(10,690), new Posn(40, 675), new Black());
		}
		else
			this.theCanvas.drawRect(new Posn(0,650), 50, 50, new Red());
			this.theCanvas.drawLine(new Posn(10, 660), new Posn(40, 660), new Black());
			this.theCanvas.drawLine(new Posn(10, 690), new Posn(40, 690), new Black());
			this.theCanvas.drawLine(new Posn(10, 660), new Posn(10, 690), new Black());
			this.theCanvas.drawLine(new Posn(40, 660), new Posn(40, 690), new Black());
	}
	
	
	// Under here lies all the drawing functions.
	/**
	 * Draws the grid for the sequencer on the Canvas
	 */
	public void drawGrid(){
		//horizontal lines
		for(int i = 563; i > 50; i--){
			if((i - 562) % 48 == 0) //octaves
				this.theCanvas.drawLine(new Posn(50, i), new Posn (850, i), new Black());
			if((i - 562) % 4 == 0) //notes
				this.theCanvas.drawLine(new Posn(50, i), new Posn (60, i), new Red());
		}

		//going vertical
		for(int i = 51; i < 850; i++){
			if((i - 50) % 80 == 0)
				this.theCanvas.drawLine(new Posn(i, 50), new Posn(i, 562), new Green());
		}
	}
	
	/**
	 * Draws the notes for the sequencer on the Canvas
	 */
	public void drawNotes(){
		for(int i = 0; i < NOTES.size(); i++)
			NOTES.get(i).draw(this.theCanvas);
	}
	
	/**
	 * Draws the cursor at the current tick position on the graph
	 */
	public void drawNowTime(){
		this.theCanvas.drawRect(new Posn(TICK_COUNTER + 50, 50), 4, 516, new Yellow());
	}
	
}