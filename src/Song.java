import java.util.ArrayList;

import tunes.Tune;

//Final Project
//Robby Grodin
//8903
/**
 * This class represents the full song as a master track.
 * Each index at the track contains all the notes to be played
 * at a specific time, which can be referenced by checking the index.
 * @author captainrobby37
 * 4.19.2010
 */

public class Song{
	// Declare fields

	// Track that contains an ArrayList<Tune> for each tick
	public ArrayList<ArrayList<Tune>> MASTER_TRACK = new ArrayList<ArrayList<Tune>>();

	/**
	 * Creator for the method <code>Song</code>
	 */
	public Song(){
		songInit();
	}

	/**
	 * Adds tunes to their respective index in the <code>MASTER_TRACK</code>
	 * @param midichannel Designates the MidiChannel for the tune
	 * @param note The MIDI note message, decides pitch.
	 * @param time Determines what time the note is to occur, based on what index it is placed at in the track array
	 */
	public void addEvent(int midichannel, int note, int time){
		
		MASTER_TRACK.get(time).add(new Tune(midichannel, new tunes.Note(note)));
	}


	/**
	 * This method puts silent notes in the MASTER_TRACK, at each index.
	 * Therefore, when there are no notes at the current time, the onTick
	 * method can still add notes without getting out of bounds exceptions.
	 * The process of adding a note at every tick, without searching for a note first,
	 * reduces the amount of work the computer has to do, and allows for better
	 * playback when there are a lot of notes to be played.
	 */
	public void songInit(){
		// ArrayList<Tune> comprising of a single silent note
		ArrayList<Tune> a = new ArrayList<Tune>();
		a.add(new Tune(2, new tunes.Note(0)));

		for(int i = 0; i < 799; i++){
			MASTER_TRACK.add(i, a);
			a = new ArrayList<Tune>();
		}
	}
	
	/**
	 * When called, this method returns the <code>ArrayList<Tune></code>.
	 * at the given index.
	 * @param time Index from which to receive the notes
	 * @return the <code>Iterable</code> of notes that happen at the given tick time
	 */
	public ArrayList<Tune> getNotes(int time){
		return MASTER_TRACK.get(time);
	}
	
}