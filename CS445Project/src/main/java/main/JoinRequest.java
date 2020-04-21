package main;

public class JoinRequest {
	
	private Integer jid;
	private Integer aid;
	private Integer passengers;
	private Boolean ride_confirmed;
	private Boolean pickup_confirmed;
	

	public JoinRequest(int aid, int passengers) {
		this.jid = UniqueIDGenerator.getUniqueID();
		this.aid = aid;
		this.passengers = passengers;
	}
	
	public JoinRequest(int jid) {
		this.jid = jid;
	}
	
	public int getJid() {
		return this.jid;
	}
	
	public int getAid() {
		return this.aid;
	}
	
	public void setRideConfirmed(boolean b) {
		this.ride_confirmed = b;
	}
	
	public void setPickupConfirmed(boolean b) {
		this.pickup_confirmed = b;
	}
}
