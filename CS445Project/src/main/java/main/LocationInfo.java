package main;

public class LocationInfo {

	private String from_city;
	private String from_zip;
	private String to_city;
	private String to_zip;
	private Integer count;
	
	public LocationInfo(String departure, String departureZIP, String destination, String destinationZIP) {
		this.from_city = departure;
		this.from_zip = departureZIP;
		this.to_city = destination;
		this.to_zip = destinationZIP;
	}
	
	public LocationInfo(String from, String to, int count) {
		this.from_zip = from;
		this.to_zip = to;
		this.count = count;
	}
	
	public String getDeparture() {
		return this.from_city;
	}
	
	public String getDepartureZip() {
		return this.from_zip;
	}
	
	public String getDestination() {
		return this.to_city;
	}
	
	public String getDestinationZip() {
		return this.to_zip;
	}
	
}
