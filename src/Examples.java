import isdraw.World;

import java.util.ArrayList;

import geometry.Posn;

import tester.Tester;
import tunes.*;

//Final Project
//Robby Grodin
//8903

public class Examples{
	
	Examples(){}

	// examples of data for the Stage class
	Sequencer sq1 = new Sequencer(new Composer(new Posn(50, 562)));
	Sequencer sq2 = new Sequencer(new Composer(new Posn(850, 50)));
	Sequencer sq3 = new Sequencer(new Composer(new Posn(100, 100)));
	
	public static void main(String[] arv){
		Examples ex = new Examples();
		
		Sequencer s1 = new Sequencer(new Composer(new Posn(50, 562)));
		
		Tester.runFullReport(ex);
		s1.bigBang(1000, 800, .027);
	}
	
	// reset a Sequencer
	private void worldReset(Sequencer w){
		w.COUNTER = 0;
		w.TICK_COUNTER = 0;
		w.song = new Song();
		w.NOTES = new ArrayList<Note>();
	}
	
	/*
	 * TEST CASES
	 */
	

	/***
	 *  This tests onKeyEvents
	 */
	public void testKeyEvent(Tester t){
		
		
		//I'm not going to test move for the class Composer elsewhere, it's tested here.
	sq1.onKeyEvent("left");
	t.checkExpect(sq1.COMP.posn.x == 50, true);

	sq1.onKeyEvent("down");
	t.checkExpect(sq1.COMP.posn.y == 562, true);

	sq2.onKeyEvent("right");
	t.checkExpect(sq2.COMP.posn.x == 850, true);

	sq2.onKeyEvent("up");
	t.checkExpect(sq2.COMP.posn.y == 50, true);

	sq3.onKeyEvent("left");
	t.checkExpect(sq3.COMP.posn.x == 96, true);

	sq3.onKeyEvent("down");
	t.checkExpect(sq3.COMP.posn.y == 104, true);

	sq3.onKeyEvent("right");
	t.checkExpect(sq3.COMP.posn.x == 100, true);

	sq3.onKeyEvent("up");
	t.checkExpect(sq3.COMP.posn.y == 100, true);
	
	sq1.onKeyEvent("t");
	t.checkExpect(sq1.POWER == true);
	
	sq1.onKeyEvent("t");
	t.checkExpect(sq1.POWER == false);
	
	sq1.onKeyEvent("2");
	t.checkExpect(sq1.INSTR == 2);
	t.checkExpect(sq1.CHANNEL == sq1.channel2);

	sq1.onKeyEvent("3");
	t.checkExpect(sq1.INSTR == 3);
	t.checkExpect(sq1.CHANNEL == sq1.channel3);
	
	
	sq1.onKeyEvent("4");
	t.checkExpect(sq1.INSTR == 4);
	t.checkExpect(sq1.CHANNEL == sq1.channel4);
	
	
	sq1.onKeyEvent("1");
	t.checkExpect(sq1.INSTR == 1);
	t.checkExpect(sq1.CHANNEL == sq1.channel1);
	
	}
	
	/***
	 * This tests addEvent, which is called by onKeyEvent(" ").
	 * Additionally diagnostics were run by System.out.println(x),
	 * where x is the Tune that is added when addEvent is called.
	 */
	public void testAddEvent(Tester t){
		
		sq1.song.addEvent(13, 64, 120); //we first add an event to the master array
		
		t.checkExpect(sq1.song.MASTER_TRACK.get(120).get(0),(new Tune(13,new tunes.Note(64)))); // and see that it is the same
		
		sq1.song.addEvent(2, 34, 120); //add a second event
		
		t.checkExpect(sq1.song.MASTER_TRACK.get(120).get(1),(new Tune(2,new tunes.Note(34))));  // as well, it is the same
	}
	
	/***
	 * This tests onTick events
	 */
	public void testOnTick(Tester t){
		
		sq1.POWER = true;
		
		sq1.onTick(); // One tick
		t.checkExpect(sq1.COUNTER == 1);
		t.checkExpect(sq1.TICK_COUNTER == 1);
		
		sq1.onTick(); // Another tick
		t.checkExpect(sq1.COUNTER == 2);
		t.checkExpect(sq1.TICK_COUNTER == 2);
		
		sq1.POWER = false;
		sq1.onTick(); // A third tick
		t.checkExpect(sq1.COUNTER == 2);
		t.checkExpect(sq1.TICK_COUNTER == 2);
	}
	
	/***
	 * This tests flipPower
	 */
	public void testFlipPower(Tester t){
		sq1.POWER = false; // initial setting
		
		sq1.flipPower();
		t.checkExpect(sq1.POWER == true);
		
		sq1.flipPower();
		t.checkExpect(sq1.POWER == false);
	}
}