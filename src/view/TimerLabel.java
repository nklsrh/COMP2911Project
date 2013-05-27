package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;


import javax.swing.JLabel;
import javax.swing.Timer;

class TimerLabel extends JLabel implements ActionListener {
	
	
	public TimerLabel() {
		super("" + new Date());
	    Timer t = new Timer(1000, this);
	    t.start();
	}
	
	//setText needs to take in a string that will appear where the timer appears in the timer tab.
	//I can't find a way to change the format of the Date such that it contains just hours, minutes and seconds
	//and starts from 0. At this moment, this code generates a date that updates every second.
	public void actionPerformed(ActionEvent ae) {
		setText(new Date().toString());
		
	}
}
