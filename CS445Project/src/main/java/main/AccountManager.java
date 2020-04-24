package main;

import java.util.ArrayList;
import java.util.List;

public class AccountManager {
	
	private static List<Account> accounts = new ArrayList<Account>();
	
	public List<Account> viewAllAccounts() {
		List<Account> returnList = new ArrayList<Account>();
		for(int i = 0; i < accounts.size(); i++) {
			Account a = accounts.get(i);
			String name = a.getFirstName() + " " + a.getLastName();
			Account b = new Account(a.getAid(), name, a.getDateCreated(), a.getIsActive());
			returnList.add(b);
		}
		return returnList;
	}
	
	public List<Account> searchAccounts(String keyword) {
		List<Account> searchList = new ArrayList<Account>();
		List<Account> returnList = new ArrayList<Account>();
		for(int i = 0; i < accounts.size(); i++) {
			Account a = accounts.get(i);
			if(a.getFirstName().contains(keyword) || a.getLastName().contains(keyword) || a.getPhone().contains(keyword)) {
				searchList.add(a);
			}
		}
		
		for(int i = 0; i < searchList.size(); i++) {
			Account a = searchList.get(i);
			Account b = new Account(a.getAid(), a.getFirstName() + " " + a.getLastName(), a.getDateCreated(), a.getIsActive());
			returnList.add(b);
			
		}
		return returnList;
	}
	
	public Account createAccount(String fn, String ln, String ph, String pic, boolean is_active) {
		Account a = new Account(fn, ln, ph, pic, is_active);
		accounts.add(a);
		return new Account(a.getAid());
	}
	
	public int activateAccount(int aid, boolean is_active) {
		for(int i = 0; i < accounts.size(); i++) {
			Account a = accounts.get(i);
			if(a.getAid() == aid) {
				a.setActive(is_active);
				return 1;
			}
		}
		return 0;
	}
	
	public int updateAccount(int aid, String fn, String ln, String ph, String pic) {
		for(int i = 0; i < accounts.size(); i++) {
			Account a = accounts.get(i);
			if(a.getAid() == aid) {
				a.setFirstName(fn);
				a.setLastName(ln);
				if(ph != null) {
					a.setPhone(ph);
				}
				if(pic != null) {
					a.setPicture(pic);
				}
				return 1;
			}
		}
		return 0;
	}
	
	public int deleteAccount(int aid) {
		for(int i = 0; i < accounts.size(); i++) {
			Account a = accounts.get(i);
			if(a.getAid() == aid) {
				accounts.remove(i);
				return 1;
			}
		}
		return 0;
	}
	
	public Rating rateAccount(int aid, int rid, int sent_by_id, int rating, String comment) {
		Account acc = getAccount(sent_by_id);
		Ride ride = RideManager.getRide(rid);
		String fn = acc.getFirstName();
		boolean isRider = false;
		boolean isDriver = false;
		if(ride.getAid() == sent_by_id) {
			isDriver = true;
		}
		else {
			List<JoinRequest> jr = ride.getJoinRequests();
			for(int i = 0; i < jr.size(); i++) {
				if(jr.get(i).getAid() == sent_by_id) {
					isRider = true;
				}
			}
		}
		if(isRider == false && isDriver == false) {
			return null;
		}
 		Rating r = new Rating(rid, sent_by_id, fn, rating, comment);
		for(int i = 0; i < accounts.size(); i++) {
			if(accounts.get(i).getAid() == aid) {
				accounts.get(i).addRating(r);
				if(isRider == true) {
					accounts.get(i).addDriverRating(r);
				}
				else {
					accounts.get(i).addRiderRating(r);
				}
			}
		}
		return new Rating(r.getSid());
	}
	
	public Account viewDriverRatings(int aid) {
		Account a = getAccount(aid);
		List<Ride> rideList = RideManager.getRidesList();
		int rides = 0;
		List<Rating> ratingsList = a.getDriverRatings();
		for(int i = 0; i < rideList.size(); i++) {
			if(rideList.get(i).getAid() == aid) {
				rides++;
			}
		}
		int ratings = ratingsList.size();
		Double avg = null;
		if(ratings > 0) {
			int sum = 0;
			for(int i = 0; i < ratings; i++) {
				sum += ratingsList.get(i).getRating();
			}
			avg = Double.valueOf(sum)/Double.valueOf(ratings);
		}
		
		return new Account(a.getAid(), a.getFirstName(), rides, ratings, avg, ratingsList);
		
	}
	
	public Account viewRiderRatings(int aid) {
		Account a = getAccount(aid);
		List<Rating> ratingsList = a.getRiderRatings();
		int rides = ratingsList.size();
		int ratings = ratingsList.size();
		Double avg = null;
		if(ratings > 0) {
			int sum = 0;
			for(int i = 0; i < ratings; i++) {
				sum += ratingsList.get(i).getRating();
			}
			avg = Double.valueOf(sum)/Double.valueOf(ratings);
		}
		
		return new Account(a.getAid(), a.getFirstName(), rides, ratings, avg, ratingsList);
		
	}
	
	public static List<Account> getAccountList() {
		return accounts;
	}
	
	public static Account getAccount(int aid) {
		for(int i = 0; i < accounts.size(); i++) {
			if(accounts.get(i).getAid() == aid) {
				return accounts.get(i);
			}
		}
		return null;
	}
	

}
