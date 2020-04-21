package main;

public class Message {
	
	private Integer mid;
	private Integer sent_by_aid;
	private String date;
	private String body;
	
	public Message(int aid, String msg) {
		this.mid = UniqueIDGenerator.getUniqueID();
		this.sent_by_aid = aid;
		this.date = DateTime.getTimestamp().toString();
		this.body = msg;
	}
	
	public Message(int mid) {
		this.mid = mid;
	}
	
	public int getMid() {
		return this.mid;
	}

}
