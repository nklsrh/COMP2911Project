package view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimerFormat {
	private Date start;
	private Date now;
	private SimpleDateFormat sdf;
	
	// sdf should by right be 00:00:00 after running this constructor.
	public TimerFormat() {
		start = new Date();
		now = new Date();
		sdf = new SimpleDateFormat("hh:mm:ss");
		
		sdf.format(new Date(now.getTime() - start.getTime()));
	}
	
	
	public SimpleDateFormat getSDF() {
		return sdf;
	}
	
	// This doesn't work. number stuck at 00:00:00.
	public Date toDate() {
		try {
			return sdf.parse("00:00:00");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
