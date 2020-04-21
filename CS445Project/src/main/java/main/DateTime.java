package main;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DateTime {
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat("dd.MMM.yyyy.HH.mm.ss");
	private String date;
	private String time;
	
	public DateTime(String d, String t) {
		this.date = d;
		this.time = t;
	}
	public static Timestamp getTimestamp() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return timestamp;
	}
	
	public String getDate() {
		return this.date;
	}
}
