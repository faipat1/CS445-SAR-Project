package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import main.Account;
import main.AccountManager;
import main.Car;
import main.DateTime;
import main.LocationInfo;
import main.Rating;
import main.Ride;
import main.RideManager;

class AccountTests {
	

	@Test
	void test_account_creation() {
		AccountManager manager = new AccountManager();
		Account a = manager.createAccount("John", "Smith", "312-456-7890", "google.com", false);
		assertTrue(a.getAid() > 0);
	}
	
	@Test
	void test_view_accounts() {
		AccountManager manager = new AccountManager();
		Account a = manager.createAccount("John", "Doe", "312-456-7890", "google.com", false);
		List<Account> list = manager.viewAllAccounts();
		Account b = list.get(0);
		assertTrue(list.size() > 0);
	}
	
	@Test
	void test_search_accounts_success() {
		AccountManager manager = new AccountManager();
		Account a = manager.createAccount("Bob", "Doe", "312-456-7890", "google.com", false);
		List<Account> list = manager.searchAccounts("Bob");
		assertTrue(list.size() > 0);
	}
	
	@Test
	void test_search_accounts_fail() {
		AccountManager manager = new AccountManager();
		Account a = manager.createAccount("Bob", "Doe", "312-456-7890", "google.com", false);
		List<Account> list = manager.searchAccounts("asdgklwn");
		assertTrue(list.size() == 0);
	}
	
	@Test
	void test_activate_account_success() {
		AccountManager manager = new AccountManager();
		Account a = manager.createAccount("Bob", "Doe", "312-456-7890", "google.com", false);
		int aid = a.getAid();
		int success = manager.activateAccount(aid, true);
		assertTrue(success == 1);
	}
	
	@Test
	void test_activate_account_fail() {
		AccountManager manager = new AccountManager();
		Account a = manager.createAccount("Bob", "Doe", "312-456-7890", "google.com", false);
		int aid = a.getAid();
		int success = manager.activateAccount(99, true);
		assertTrue(success == 0);
	}
	
	@Test
	void test_update_account_success() {
		AccountManager manager = new AccountManager();
		Account a = manager.createAccount("Bob", "Doe", "312-456-7890", "google.com", false);
		int aid = a.getAid();
		int success = manager.updateAccount(aid, "Larry", "King", "312-000-0000", "facebook.com");
		assertTrue(success == 1);
	}
	
	@Test
	void test_update_account_fail() {
		AccountManager manager = new AccountManager();
		Account a = manager.createAccount("Bob", "Doe", "312-456-7890", "google.com", false);
		int aid = a.getAid();
		int success = manager.updateAccount(99, "Larry", "King", "312-000-0000", "facebook.com");
		assertTrue(success == 0);
	}
	
	
	
	@Test
	void test_delete_account_success() {
		AccountManager manager = new AccountManager();
		Account a = manager.createAccount("Bob", "Doe", "312-456-7890", "google.com", false);
		int aid = a.getAid();
		int success = manager.deleteAccount(aid);
		assertTrue(success == 1);
	}
	
	@Test
	void test_delete_account_fail() {
		AccountManager manager = new AccountManager();
		Account a = manager.createAccount("Bob", "Doe", "312-456-7890", "google.com", false);
		int aid = a.getAid();
		int success = manager.deleteAccount(99);
		assertTrue(success == 0);
	}
	
	@Test
	void test_rating() {
		Rating r = new Rating(5, "good");
		assertTrue(r.getRating() > 0);
	}
	
	@Test
	void test_driver_rating() {
		AccountManager manager = new AccountManager();
		Account a = manager.createAccount("Bob", "Doe", "312-456-7890", "google.com", false);
		Account b = manager.viewDriverRatings(a.getAid());
		assertTrue(b.getDriverRatings() == null);
	}
	
	@Test
	void test_rider_rating() {
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
		Rating ra = accmanager.rateAccount(b.getAid(), r.getRid(), a.getAid(), 5, "good");
		assertTrue(b.getRatings() == null);
	}
	
	@Test
	void test_rate_account_null() {
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
		int rid = r.getRid();
		Rating ra = accmanager.rateAccount(a.getAid(), r.getRid(), b.getAid(), 5, "good");
		assertTrue(ra == null);
	}
	
	@Test
	void test_rate_account() {
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
		Rating ra = accmanager.rateAccount(b.getAid(), r.getRid(), a.getAid(), 5, "good");
		assertTrue(ra.getSid() > 0);
	}
	
	

}
