package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import main.Account;
import main.AccountManager;
import main.Car;
import main.DateTime;
import main.JoinRequest;
import main.LocationInfo;
import main.Message;
import main.Ride;
import main.RideManager;


class RideTests {

	@Test
	void test_create_ride() {
		RideManager manager = new RideManager();
		Account a = new Account("John", "Smith", "312-000-0000", "google.com", true);
		LocationInfo li = new LocationInfo("Chicago" , "60061", "Skokie", "60067");
		DateTime dt = new DateTime("14-Apr-2020", "09:00");
		Car car = new Car("Audi", "A4", "Gray", "IL", "COVID19");
		int mp = 2;
		double app = 15.0;
		String conditions = "no smoking";
		Ride r = manager.createRide(a.getAid(), li, dt, car, mp, app, conditions);
		assertTrue(r.getRid() > 0);
	}
	
	@Test
	void test_update_ride() {
		RideManager manager = new RideManager();
		Account a = new Account("John", "Smith", "312-000-0000", "google.com", true);
		LocationInfo li = new LocationInfo("Chicago" , "60061", "Skokie", "60067");
		DateTime dt = new DateTime("14-Apr-2020", "09:00");
		Car car = new Car("Audi", "A4", "Gray", "IL", "COVID19");
		int mp = 2;
		double app = 15.0;
		String conditions = "no smoking";
		Ride r = manager.createRide(a.getAid(), li, dt, car, mp, app, conditions);
		DateTime dt2 = new DateTime("15-Apr-2020", "09:00");
		int success = manager.updateRide(r.getRid(), a.getAid(), li, dt2, car, mp, app, conditions);
		assertTrue(success == 1);
	}
	
	@Test
	void test_get_ride_detail_null() {
		RideManager manager = new RideManager();
		Account a = new Account("John", "Smith", "312-000-0000", "google.com", true);
		LocationInfo li = new LocationInfo("Chicago" , "60061", "Skokie", "60067");
		DateTime dt = new DateTime("14-Apr-2020", "09:00");
		Car car = new Car("Audi", "A4", "Gray", "IL", "COVID19");
		int mp = 2;
		double app = 15.0;
		String conditions = "no smoking";
		Ride r = manager.createRide(a.getAid(), li, dt, car, mp, app, conditions);
		Ride x = manager.getRideDetail(r.getRid());
		assertTrue(x == null);
	}
	
	@Test
	void test_get_ride_detail() {
		RideManager manager = new RideManager();
		AccountManager accmanager = new AccountManager();
		Account a = accmanager.createAccount("John", "Smith", "312-000-0000", "google.com", true);
		LocationInfo li = new LocationInfo("Chicago" , "60061", "Skokie", "60067");
		DateTime dt = new DateTime("14-Apr-2020", "09:00");
		Car car = new Car("Audi", "A4", "Gray", "IL", "COVID19");
		int mp = 2;
		double app = 15.0;
		String conditions = "no smoking";
		Ride r = manager.createRide(a.getAid(), li, dt, car, mp, app, conditions);
		Ride x = manager.getRideDetail(r.getRid());
		assertTrue(x.getRid() > 0);
	}
	
	
	@Test
	void test_get_rides() {
		RideManager manager = new RideManager();
		Account a = new Account("John", "Smith", "312-000-0000", "google.com", true);
		LocationInfo li = new LocationInfo("Chicago" , "60061", "Skokie", "60067");
		DateTime dt = new DateTime("14-Apr-2020", "09:00");
		Car car = new Car("Audi", "A4", "Gray", "IL", "COVID19");
		int mp = 2;
		double app = 15.0;
		String conditions = "no smoking";
		Ride r = manager.createRide(a.getAid(), li, dt, car, mp, app, conditions);
		List<Ride> list = manager.getRides();
		assertTrue(list.size() > 0);
	}
	
	@Test
	void test_search_rides_success() {
		RideManager manager = new RideManager();
		Account a = new Account("John", "Smith", "312-000-0000", "google.com", true);
		LocationInfo li = new LocationInfo("Chicago" , "60061", "Skokie", "60067");
		DateTime dt = new DateTime("14-Apr-2020", "09:00");
		Car car = new Car("Audi", "A4", "Gray", "IL", "COVID19");
		int mp = 2;
		double app = 15.0;
		String conditions = "no smoking";
		Ride r = manager.createRide(a.getAid(), li, dt, car, mp, app, conditions);
		List<Ride> list = manager.searchRides("Chicago", "", "");
		assertTrue(list.size() > 0);
	}
	
	@Test
	void test_search_rides_fail() {
		RideManager manager = new RideManager();
		Account a = new Account("John", "Smith", "312-000-0000", "google.com", true);
		LocationInfo li = new LocationInfo("Chicago" , "60061", "Skokie", "60067");
		DateTime dt = new DateTime("14-Apr-2020", "09:00");
		Car car = new Car("Audi", "A4", "Gray", "IL", "COVID19");
		int mp = 2;
		double app = 15.0;
		String conditions = "no smoking";
		Ride r = manager.createRide(a.getAid(), li, dt, car, mp, app, conditions);
		List<Ride> list = manager.searchRides("asdads", "adaisdnsa", "weiofmwoief");
		assertTrue(list.size() == 0);
	}
	
	@Test
	void test_delete_ride_fail() {
		RideManager manager = new RideManager();
		Account a = new Account("John", "Smith", "312-000-0000", "google.com", true);
		LocationInfo li = new LocationInfo("Chicago" , "60061", "Skokie", "60067");
		DateTime dt = new DateTime("14-Apr-2020", "09:00");
		Car car = new Car("Audi", "A4", "Gray", "IL", "COVID19");
		int mp = 2;
		double app = 15.0;
		String conditions = "no smoking";
		Ride r = manager.createRide(a.getAid(), li, dt, car, mp, app, conditions);
		int success = manager.deleteRide(99);
		assertTrue(success == 0);
	}
	
	@Test
	void test_delete_ride_success() {
		RideManager manager = new RideManager();
		Account a = new Account("John", "Smith", "312-000-0000", "google.com", true);
		LocationInfo li = new LocationInfo("Chicago" , "60061", "Skokie", "60067");
		DateTime dt = new DateTime("14-Apr-2020", "09:00");
		Car car = new Car("Audi", "A4", "Gray", "IL", "COVID19");
		int mp = 2;
		double app = 15.0;
		String conditions = "no smoking";
		Ride r = manager.createRide(a.getAid(), li, dt, car, mp, app, conditions);
		int success = manager.deleteRide(r.getRid());
		assertTrue(success == 1);
	}
	
	@Test
	void test_add_message() {
		RideManager manager = new RideManager();
		Account a = new Account("John", "Smith", "312-000-0000", "google.com", true);
		LocationInfo li = new LocationInfo("Chicago" , "60061", "Skokie", "60067");
		DateTime dt = new DateTime("14-Apr-2020", "09:00");
		Car car = new Car("Audi", "A4", "Gray", "IL", "COVID19");
		int mp = 2;
		double app = 15.0;
		String conditions = "no smoking";
		Ride r = manager.createRide(a.getAid(), li, dt, car, mp, app, conditions);
		Message m = manager.addMessage(r.getRid(), a.getAid(), "Hello");
		assertTrue(m.getMid() > 0);
	}
	
	@Test
	void test_get_messages() {
		RideManager manager = new RideManager();
		Account a = new Account("John", "Smith", "312-000-0000", "google.com", true);
		LocationInfo li = new LocationInfo("Chicago" , "60061", "Skokie", "60067");
		DateTime dt = new DateTime("14-Apr-2020", "09:00");
		Car car = new Car("Audi", "A4", "Gray", "IL", "COVID19");
		int mp = 2;
		double app = 15.0;
		String conditions = "no smoking";
		Ride r = manager.createRide(a.getAid(), li, dt, car, mp, app, conditions);
		Message m = manager.addMessage(r.getRid(), a.getAid(), "Hello");
		List<Message> list = manager.getMessages(r.getRid());
		assertTrue(list.size() > 0);
	}
	
	@Test
	void test_create_join_request() {
		AccountManager accmanager = new AccountManager();
		Account a = accmanager.createAccount("Bob", "Doe", "312-456-7890", "google.com", true);
		Account b = accmanager.createAccount("John", "Smith", "312-000-0000", "Asda", true);
		RideManager manager = new RideManager();
		LocationInfo li = new LocationInfo("Chicago" , "60061", "Skokie", "60067");
		DateTime dt = new DateTime("14-Apr-2020", "09:00");
		Car car = new Car("Audi", "A4", "Gray", "IL", "COVID19");
		int mp = 2;
		double app = 15.0;
		String conditions = "no smoking";
		Ride r = manager.createRide(a.getAid(), li, dt, car, mp, app, conditions);
		JoinRequest jr = manager.createJoinRequest(r.getRid(), b.getAid(), 2);
		assertTrue(jr.getJid() > 0);
	}
	
	@Test
	void test_confirm_ride_request() {
		AccountManager accmanager = new AccountManager();
		Account a = accmanager.createAccount("Bob", "Doe", "312-456-7890", "google.com", true);
		Account b = accmanager.createAccount("John", "Smith", "312-000-0000", "Asda", true);
		RideManager manager = new RideManager();
		LocationInfo li = new LocationInfo("Chicago" , "60061", "Skokie", "60067");
		DateTime dt = new DateTime("14-Apr-2020", "09:00");
		Car car = new Car("Audi", "A4", "Gray", "IL", "COVID19");
		int mp = 2;
		double app = 15.0;
		String conditions = "no smoking";
		Ride r = manager.createRide(a.getAid(), li, dt, car, mp, app, conditions);
		JoinRequest jr = manager.createJoinRequest(r.getRid(), b.getAid(), 2);
		int success = manager.confirmRideRequest(r.getRid(), jr.getJid(), true);
		assertTrue(success == 1);
	}
	
	@Test
	void test_confirm_pickup() {
		AccountManager accmanager = new AccountManager();
		Account a = accmanager.createAccount("Bob", "Doe", "312-456-7890", "google.com", true);
		Account b = accmanager.createAccount("John", "Smith", "312-000-0000", "Asda", true);
		RideManager manager = new RideManager();
		LocationInfo li = new LocationInfo("Chicago" , "60061", "Skokie", "60067");
		DateTime dt = new DateTime("14-Apr-2020", "09:00");
		Car car = new Car("Audi", "A4", "Gray", "IL", "COVID19");
		int mp = 2;
		double app = 15.0;
		String conditions = "no smoking";
		Ride r = manager.createRide(a.getAid(), li, dt, car, mp, app, conditions);
		JoinRequest jr = manager.createJoinRequest(r.getRid(), b.getAid(), 2);
		int success = manager.confirmPickup(r.getRid(), jr.getJid(), true);
		assertTrue(success == 1);
	}

}
