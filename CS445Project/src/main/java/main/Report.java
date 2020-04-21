package main;

import java.util.List;

public class Report {
	
	private Integer pid;
	private String name;
	private String start_date;
	private String end_date;
	private Integer rides;
	private List<LocationInfo> detail;
	
	public Report(int pid, String name) {
		this.pid = pid;
		this.name = name;
	}
	
	public Report(int pid, String name, String from, String to, int rides, List<LocationInfo> detail) {
		this.pid = pid;
		this.name = name;
		this.start_date = from;
		this.end_date = to;
		this.rides = rides;
		this.detail = detail;
	}
	
	public int getPid() {
		return this.pid;
	}
	
	public String getName() {
		return this.name;
	}

}
