package main;

import java.util.ArrayList;
import java.util.List;

public class Ride {
	
	private Integer rid;
	private Integer aid;
	private LocationInfo location_info;
	private DateTime date_time;
	private Car car_info;
	private Integer max_passengers;
	private Double amount_per_passenger;
	private String conditions;
	private List<JoinRequest> joinRequests;
	private List<Message> messages;
	private String driver;
	private String driver_picture;
	private Integer rides;
	private Integer ratings;
	private Double average_rating;
	private List<Rating> comments_about_driver;
	
	
	
	
	public Ride(int accID, LocationInfo li, DateTime dt, Car c, int mp, Double app, String cond) {
		this.rid = UniqueIDGenerator.getUniqueID();
		this.aid = accID;
		this.location_info = li;
		this.date_time = dt;
		this.car_info = c;
		this.max_passengers = mp;
		this.amount_per_passenger = app;
		this.conditions = cond;
		this.joinRequests = new ArrayList<JoinRequest>();
		this.messages = new ArrayList<Message>();
		
	}
	
	public Ride(int rid) {
		this.rid = rid;
	}
	
	public Ride(int rid, LocationInfo li, DateTime dt) {
		this.rid = rid;
		this.location_info = li;
		this.date_time = dt;
	}
	
	public Ride(int rid, LocationInfo li, DateTime dt, Car car, int mp, Double app, String cond, String driver, String pic, int rides, int ratings, Double avg, List<Rating> list) {
		this.rid = rid;
		this.location_info = li;
		this.date_time = dt;
		this.car_info = car;
		this.max_passengers = mp;
		this.amount_per_passenger = app;
		this.conditions = cond;
		this.driver = driver;
		this.driver_picture = pic;
		this.rides = rides;
		this.ratings = ratings;
		this.average_rating = avg;
		this.comments_about_driver = list;
	}
	
	public void setLocationInfo(LocationInfo li) {
		this.location_info = li;
	}
	
	public void setDateTime(DateTime dt) {
		this.date_time = dt;
	}
	
	public Car getCar() {
		return this.car_info;
	}
	
	public void setCar(Car car) {
		this.car_info = car;
	}
	
	public void setMaxPassengers(int mp) {
		this.max_passengers = mp;
	}
	
	public void setAmountPerPassenger(double app) {
		this.amount_per_passenger = app;
	}
	
	public void setConditions(String cond) {
		this.conditions = cond;
	}
	
	public int getRid() {
		return this.rid;
	}
	
	public int getAid() {
		return this.aid;
	}
	
	public LocationInfo getLocationInfo() {
		return this.location_info;
	}
	
	public DateTime getDateTime() {
		return this.date_time;
	}
	
	public void addJoinRequest(JoinRequest jr) {
		this.joinRequests.add(jr);
	}
	
	public List<JoinRequest> getJoinRequests() {
		return this.joinRequests;
	}
	
	public void addMessage(Message m) {
		this.messages.add(m);
	}

	public List<Message> getMessages() {
		return this.messages;
	}
	
	public int getMaxPassengers() {
		return this.max_passengers;
	}
	
	public Double getAmountPerPassenger() {
		return this.amount_per_passenger;
	}
	
	public String getConditions() {
		return this.conditions;
	}
}
