package main;

import java.util.ArrayList;
import java.util.List;

public class RideManager {
	
	private static List<Ride> rides = new ArrayList<Ride>();
	
	public List<Ride> getRides() {
		List<Ride> returnList = new ArrayList<Ride>();
		for(int i = 0; i < rides.size(); i++) {
			Ride a = rides.get(i);
			Ride b = new Ride(a.getRid(), a.getLocationInfo(), a.getDateTime());
			returnList.add(b);
		}
		return returnList;
	}
	
	public Ride getRideDetail(int rid) {
		Ride r = getRide(rid);
		if(r == null) {
			return null;
		}
		int aid = r.getAid();
		Account a = AccountManager.getAccount(aid);
		if(a == null) {
			return null;
		}
		String fn = a.getFirstName();
		String pic = a.getPicture();
		List<Rating> ratings = a.getRatings();
		int numRides = ratings.size();
		int numRatings = ratings.size();
		int sumRatings = 0;
		if(numRatings > 0) {
			for(int i = 0; i < ratings.size(); i++) {
				sumRatings+= ratings.get(i).getRating();
			}
		}
		Double avgRating = null;
		if(numRatings > 0) {
			avgRating = Double.valueOf(sumRatings)/Double.valueOf(numRatings);
		}
		return new Ride(r.getRid(), r.getLocationInfo(), r.getDateTime(), r.getCar(), r.getMaxPassengers(), r.getAmountPerPassenger(), r.getConditions(), fn, pic, numRides, numRatings, avgRating, ratings);
 	}
	
	public List<Ride> searchRides(String from, String to, String date) {
		List<Ride> searchList = new ArrayList<Ride>();
		List<Ride> returnList = new ArrayList<Ride>();
		for(int i = 0; i < rides.size(); i++) {
			Ride r = rides.get(i);
			if(r.getLocationInfo().getDeparture().equalsIgnoreCase(from)
					|| r.getLocationInfo().getDestination().equalsIgnoreCase(to) || r.getDateTime().getDate().equalsIgnoreCase(date)) {
				searchList.add(r);
			}
		}
		for(int i = 0; i < searchList.size(); i++) {
			Ride a = searchList.get(i);
			Ride b = new Ride(a.getRid(), a.getLocationInfo(), a.getDateTime());
			returnList.add(b);
		}
		return returnList;
	}
	
	
	public Ride createRide(int aid, LocationInfo li, DateTime dt, Car car, int mp, Double app, String cond) {
		List<Account> accs = AccountManager.getAccountList();
		for(int i = 0; i < accs.size(); i++) {
			if(accs.get(i).getAid() == aid) {
				if(accs.get(i).getIsActive() == false) {
					return null;
				}
			}
		}
		Ride r = new Ride(aid, li, dt, car, mp, app, cond);
		rides.add(r);
		return new Ride(r.getRid());
	}
	
	public int updateRide(int rid, int aid, LocationInfo li, DateTime dt, Car car, int mp, double app, String cond) {
		for(int i = 0; i < rides.size(); i++) {
			if(rides.get(i).getRid() == rid) {
				rides.get(i).setLocationInfo(li);
				rides.get(i).setDateTime(dt);
				rides.get(i).setCar(car);
				rides.get(i).setMaxPassengers(mp);
				rides.get(i).setAmountPerPassenger(app);
				rides.get(i).setConditions(cond);
				return 1;
			}
		}
		return 0;
	}
	
	public int deleteRide(int rid) {
		for(int i = 0; i < rides.size(); i++) {
			if(rides.get(i).getRid() == rid) {
				rides.remove(i);
				return 1;
			}
		}
		return 0;
	}
	
	public JoinRequest createJoinRequest(int rid, int aid, int passengers) {
		JoinRequest jr = new JoinRequest(aid, passengers);
		Account a = AccountManager.getAccount(aid);
		if(a.getIsActive() == false) {
			return null;
		}
		for(int i = 0; i < rides.size(); i++) {
			if(rides.get(i).getRid() == rid) {
				rides.get(i).addJoinRequest(jr);
			}
		}
		return new JoinRequest(jr.getJid());
	}
	
	public int confirmRideRequest(int rid, int jid, boolean confirmed) {
		for(int i = 0; i < rides.size(); i++) {
			Ride r = rides.get(i);
			if(r.getRid() == rid) {
				List<JoinRequest> jrList = r.getJoinRequests();
				for(int j = 0; j < jrList.size(); j++) {
					JoinRequest jr = jrList.get(j);
					if(jr.getJid() == jid) {
						jr.setRideConfirmed(confirmed);
						return 1;
					}
				}
			}
		}
		return 0;
	}
	
	public int confirmPickup(int rid, int jid, boolean confirmed) {
		for(int i = 0; i < rides.size(); i++) {
			Ride r = rides.get(i);
			if(r.getRid() == rid) {
				List<JoinRequest> jrList = r.getJoinRequests();
				for(int j = 0; j < jrList.size(); j++) {
					JoinRequest jr = jrList.get(j);
					if(jr.getJid() == jid) {
						jr.setPickupConfirmed(confirmed);
						return 1;
					}
				}
			}
		}
		return 0;
	}

	public Message addMessage(int rid, int aid, String msg) { 
		Message m = new Message(aid, msg);
		for(int i = 0; i < rides.size(); i++) {
			if(rides.get(i).getRid() == rid) {
				rides.get(i).addMessage(m);
			}
		}
		return new Message(m.getMid());
	}
	
	public List<Message> getMessages(int rid) {
		List<Message> messagesList = new ArrayList<Message>();
		for(int i = 0; i < rides.size(); i++) {
			if(rides.get(i).getRid() == rid) {
				messagesList = rides.get(i).getMessages();
			}
		}
		return messagesList;
	}
	
	public static List<Ride> getRidesList() {
		return rides;
	}
	
	public static Ride getRide(int rid) {
		for(int i = 0; i < rides.size(); i++) {
			if(rides.get(i).getRid() == rid) {
				return rides.get(i);
			}
		}
		return null;
	}
}

