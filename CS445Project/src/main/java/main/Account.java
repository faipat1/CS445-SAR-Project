package main;

import java.util.ArrayList;
import java.util.List;

public class Account {
	
	private Integer aid;
	private String name;
	private String first_name;
	private String last_name;
	private String phone;
	private String picture;
	private String date_created;
	private Boolean is_active;
	private Integer rides;
	private Integer ratings;
	private Double average_rating;
	private List<Rating> detail;
	private List<Rating> driverRatings;
	private List<Rating> riderRatings;
	
	public Account(String fn, String ln, String ph, String pic, boolean active) {
		this.aid = UniqueIDGenerator.getUniqueID();
		this.first_name = fn;
		this.last_name = ln;
		this.phone = ph;
		this.picture = pic;
		this.is_active = active;
		this.date_created = DateTime.getTimestamp().toString();
		this.detail = new ArrayList<Rating>();
		this.driverRatings = new ArrayList<Rating>();
		this.riderRatings = new ArrayList<Rating>();
	}
	
	public Account(int aid) {
		this.aid = aid;
	}
	
	public Account(int aid, String name, String date_created, boolean is_active) {
		this.aid = aid;
		this.name = name;
		this.date_created = date_created;
		this.is_active = is_active;
	}
	
	public Account(int aid, String fn, int rides, int ratings, Double avg, List<Rating> list) {
		this.aid = aid;
		this.first_name = fn;
		this.rides = rides;
		this.ratings = ratings;
		this.average_rating = avg;
		this.detail = list;
	}
	
	public int getAid() {
		return this.aid;
	}
	
	public void setFirstName(String fn) {
		this.first_name = fn;
	}
	
	public String getFirstName() {
		return this.first_name;
	}
	
	public void setLastName(String ln) {
		this.last_name = ln;
	}
	
	public String getLastName() {
		return this.last_name;
	}
	
	public void setPhone(String ph) {
		this.phone = ph;
	}
	
	public String getPhone() {
		return this.phone;
	}
	
	public String getPicture() {
		return this.picture;
	}
	
	public void setPicture(String pic) {
		this.picture = pic;
	}
	
	public void setActive(boolean is_active) { 
		this.is_active = is_active;
	}
	
	public boolean getIsActive() {
		return this.is_active;
	}
	
	public String getDateCreated() {
		return this.date_created;
	}
	
	public void addRating(Rating r) {
		this.detail.add(r);
	}
	
	public void addDriverRating(Rating r) {
		this.driverRatings.add(r);
	}
	
	public void addRiderRating(Rating r) {
		this.riderRatings.add(r);
	}
	
	public List<Rating> getRatings() {
		return this.detail;
	}
	
	public List<Rating> getDriverRatings() {
		return this.driverRatings;
	}
	
	public List<Rating> getRiderRatings() { 
		return this.riderRatings;
	}
}

