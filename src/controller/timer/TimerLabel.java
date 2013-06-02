package controller.timer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;


import javax.swing.JLabel;
import javax.swing.Timer;

/**
 * 
 * This class provides a TimerLabel object which is an extension of JLabel. It displays a timer in minutes
 * and seconds which updates itself every second and starts from 0:00 (0 minutes, 0 seconds). It implements
 * ActionListener so that the timer label can update itself correctly every second.
 * 
 * @author Ryan Tan, Nikhil Suresh and Nicholas Ho
 */
public class TimerLabel extends JLabel implements ActionListener {
	
	/** The start time. */
	private long startTime;
	
	/**
	 * timerlabel constructor: This constructor produces a new TimerLabel 
	 * object with a delay value of 1000ms (1 second), and starts the timer.
	 */
	public TimerLabel() {
		super();
	    Timer t = new Timer(1000, this);
	    startTime = new Date().getTime();
	    t.start();
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
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
