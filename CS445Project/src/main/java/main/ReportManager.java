package main;

import java.util.ArrayList;
import java.util.List;

public class ReportManager {
	
	private static List<Report> reports = new ArrayList<Report>();
	
	public List<Report> viewAllReports() {
		List<Report> returnList = new ArrayList<Report>();
		returnList.add(new Report(907, "Rides posted between two dates."));
		returnList.add(new Report(911, "Rides taken between two dates."));
		return returnList;
	}

	public Report getReport(int pid, String from, String to) {
		List<Ride> ridesList = RideManager.getRidesList();
		List<LocationInfo> detail = new ArrayList<LocationInfo>();
		if(from.length() == 0 && to.length() == 0) {
			for(int i = 0; i < ridesList.size(); i++) {
				Ride r = ridesList.get(i);
				detail.add(new LocationInfo(r.getLocationInfo().getDepartureZip(), r.getLocationInfo().getDestinationZip(), 1));
			}
		}
		else if(from.length() > 0 && to.length() == 0) {
			for(int i = 0; i < ridesList.size(); i++) {
				Ride r = ridesList.get(i);
				if(Integer.valueOf(r.getDateTime().getDate().substring(0, 2)) > Integer.valueOf(from.substring(0, 2))) {
					detail.add(new LocationInfo(r.getLocationInfo().getDepartureZip(), r.getLocationInfo().getDestinationZip(), 1));
				}
			}
		}
		else {
			for(int i = 0; i < ridesList.size(); i++) {
				Ride r = ridesList.get(i);
				if(r.getDateTime().getDate().equalsIgnoreCase(from) || r.getDateTime().getDate().equalsIgnoreCase(to)) {
					detail.add(new LocationInfo(r.getLocationInfo().getDepartureZip(), r.getLocationInfo().getDestinationZip(), 1));
				}
			}
		}
		Report r;
		if(pid == 907) {
			r = new Report(pid, "Rides posted between two dates", from, to, detail.size(), detail);
		}
		else {
			r = new Report(pid, "Rides taken between two dates", from, to, detail.size(), detail);
		}
		
		reports.add(r);
		return r;
		
		
	}

}
