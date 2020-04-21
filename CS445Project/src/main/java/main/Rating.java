package main;

public class Rating {
	
	private Integer sid;
	private Integer rid;
	private Integer sent_by_id;
	private String first_name;
	private String date;
	private Integer rating;
	private String comment;
	
	public Rating(int rating, String comment) {
		this.sid = UniqueIDGenerator.getUniqueID();
		this.rating = rating;
		this.comment = comment;
	}
	
	public Rating(int rid, int sent, String fn, int rating, String comment) {
		this.sid = UniqueIDGenerator.getUniqueID();
		this.rid = rid;
		this.sent_by_id = sent;
		this.first_name = fn;
		this.rating = rating;
		this.comment = comment;
		this.date = RideManager.getRide(rid).getDateTime().getDate();
	}
	
	public Rating(int sid) {
		this.sid = sid;
	}
	
	public int getSid() {
		return this.sid;
	}

	public int getRid() {
		return this.rid;
	}
	
	public int getRating() {
		return this.rating;
	}
}
