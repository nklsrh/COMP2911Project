package controller.timer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;


import javax.swing.JLabel;
import javax.swing.Timer;

public class TimerLabel extends JLabel implements ActionListener {
	private long startTime;
	
	public TimerLabel() {
		super();
	    Timer t = new Timer(1000, this);
	    startTime = new Date().getTime();
	    t.start();
	}
	
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
