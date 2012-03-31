README v1.5

TO LAUNCH: java -jar Qwortet.jar OR double-click the Qwortet.jar file.

The Controls:

When the program is started, the sequencer will be empty. To create notes, use the arrow keys to move the cursor and the space bar to place the note. Pressing T will begin moving the Now Time indicator and the composition will be played using your computer’s built-in general MIDI synthesizer. At any time the Now Time indicator can be paused or restarted using T. To change the instrument that is currently selected, press the number key that matches the desired instrument number, designated in the legend.

The Interface:

1.	Sequencer
a.	This is where your composition will be created. Using the arrow keys, place notes on the graph to arrange your piece. The general MIDI synthesizer is polyphonic, so notes can be stacked to create chords, even between instruments.
2.	Note
a.	Notes indicate three things:
i.	Time: The X-axis represents time. The composition begins at the leftmost part of the graph, and reads towards the right. At the end, the song will loop.
ii.	Pitch: The Y-axis represents musical pitch, notated as MIDI note values (0-127). The higher up your note is placed, the higher the pitch will be, and vice versa.
iii.	Instrument: The color of the note indicates which MIDI voice will be generated. 
3.	Instruments
a.	This key shows which color represents which MIDI voice, and which number can be used to select them.
4.	Now Time indicator
a.	When the song is playing, the Now Time indicator will scroll from left to right. As it crosses notes, they will be played through your computer’s speakers. When paused, the indicator will be stopped. When the indicator reaches the end of the grid, it will start over again at the far left, creating a musical loop. The user is able to add notes to the composition before, during, and after the Now Time indicator has been started and stopped.
5.	Current Position
a.	This readout shows information about the cursor’s position.
i.	NOTE corresponds to the MIDI note number.
ii.	TIME corresponds to the step at which the note will be played.
6.	Play Indicator
a.	This indicator shows whether or not the composition is currently being played.
