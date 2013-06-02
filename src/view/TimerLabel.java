package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;


import javax.swing.JLabel;
import javax.swing.Timer;

/**
 * This class provides a TimerLabel object which is an extension of JLabel. It displays a timer in minutes
 * and seconds which updates itself every second and starts from 0:00 (0 minutes, 0 seconds). It implements
 * ActionListener so that the timer label can update itself correctly every second.
 * 
 * @author Ryan Tan, Nikhil Suresh and Nicholas Ho
 */
class TimerLabel extends JLabel implements ActionListener {
	private long startTime;
	
	/**
	 * The constructor sets the timer to update every 1000 milliseconds(1 second)
	 */
	public TimerLabel() {
		super();
	    Timer t = new Timer(1000, this);
	    startTime = new Date().getTime();
	    t.start();
	}
	
	/**
	 * This method takes one initial time as a reference, then takes another time every subsequent, 
	 * subtracting the new time from the old time to give a representation of the time that has passed.
	 */
	public void actionPerformed(ActionEvent ae) {
		// getTime() gets the time in milliseconds, conversion is required.
		long seconds = ((new Date().getTime() - startTime)/1000)%60;
		long minutes = ((new Date().getTime() - startTime)/1000)/60;
		
		if (seconds < 10) {
			setText(minutes + ":0" + seconds);
		} else {
			setText(minutes + ":" + seconds);
		}
	}
}
