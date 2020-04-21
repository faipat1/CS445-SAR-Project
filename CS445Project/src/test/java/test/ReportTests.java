package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import main.Account;
import main.Car;
import main.DateTime;
import main.LocationInfo;
import main.Report;
import main.ReportManager;
import main.Ride;
import main.RideManager;

import org.junit.jupiter.api.Test;

class ReportTests {

	@Test
	void test_view_all_reports() {
		ReportManager manager = new ReportManager();
		List<Report> reports = manager.viewAllReports();
		assertTrue(reports.size() == 2);
	}
	
	@Test
	void test_get_report_all() {
		ReportManager reportManager = new ReportManager();
		RideManager manager = new RideManager();
		Account a = new Account("John", "Smith", "312-000-0000", "google.com", true);
		LocationInfo li = new LocationInfo("Chicago" , "60061", "Skokie", "60067");
		DateTime dt = new DateTime("14-Apr-2020", "09:00");
		Car car = new Car("Audi", "A4", "Gray", "IL", "COVID19");
		int mp = 2;
		double app = 15.0;
		String conditions = "no smoking";
		Ride r = manager.createRide(a.getAid(), li, dt, car, mp, app, conditions);
		Report report = reportManager.getReport(907, "", "");
		assertTrue(report.getPid() == 907);
	}

}
