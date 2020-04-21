package test;

import static org.junit.jupiter.api.Assertions.*;

import main.REST_controller;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import org.junit.jupiter.api.Test;

class ControllerTests {
	
	REST_controller controller = new REST_controller();

	@Test
	void test_create_account() {
		Response r = controller.createAccount("{\n" + 
				"	\"first_name\": \"John\",\n" + 
				"	\"last_name\": \"Smith\",\n" + 
				"	\"phone\": \"312-456-7890\",\n" + 
				"	\"picture\": \"http://example.com/images/john-smith.jpeg\",\n" + 
				"	\"is_active\": false\n" + 
				"}");
		assertTrue(r.getStatus() == 201);
	}
	
	@Test
	void test_activate_account() {
		controller.createAccount("{\n" + 
				"	\"first_name\": \"John\",\n" + 
				"	\"last_name\": \"Smith\",\n" + 
				"	\"phone\": \"312-456-7890\",\n" + 
				"	\"picture\": \"http://example.com/images/john-smith.jpeg\",\n" + 
				"	\"is_active\": false\n" + 
				"}");
		Response r = controller.activateAccount(1, "{\n" + 
				"	\"first_name\": \"John\",\n" + 
				"	\"last_name\": \"Smith\",\n" + 
				"	\"phone\": \"312-456-7809\",\n" + 
				"	\"picture\": \"http://example.com/images/john-smith.jpeg\",\n" + 
				"	\"is_active\": false\n" + 
				"}");
		assertTrue(r.getStatus() == 204);
	}
	
	@Test
	void test_update_account() {
		controller.createAccount("{\n" + 
				"	\"first_name\": \"John\",\n" + 
				"	\"last_name\": \"Smith\",\n" + 
				"	\"phone\": \"312-456-7890\",\n" + 
				"	\"picture\": \"http://example.com/images/john-smith.jpeg\",\n" + 
				"	\"is_active\": false\n" + 
				"}");
		Response r = controller.updateAccount(1, "{\n" + 
				"	\"first_name\": \"John\",\n" + 
				"	\"last_name\": \"Smith\",\n" + 
				"	\"phone\": \"312-456-7809\",\n" + 
				"	\"picture\": \"http://example.com/images/john-smith.jpeg\",\n" + 
				"	\"is_active\": false\n" + 
				"}");
		assertTrue(r.getStatus() == 204);
	}
	
	@Test
	void test_delete_account() {
		controller.createAccount("{\n" + 
				"	\"first_name\": \"John\",\n" + 
				"	\"last_name\": \"Smith\",\n" + 
				"	\"phone\": \"312-456-7890\",\n" + 
				"	\"picture\": \"http://example.com/images/john-smith.jpeg\",\n" + 
				"	\"is_active\": false\n" + 
				"}");
		Response r = controller.deleteAccount(1);
		assertTrue(r.getStatus() == 204);
	}
	
	@Test
	void test_create_ride() {
		controller.createAccount("{\n" + 
				"	\"first_name\": \"John\",\n" + 
				"	\"last_name\": \"Smith\",\n" + 
				"	\"phone\": \"312-456-7890\",\n" + 
				"	\"picture\": \"http://example.com/images/john-smith.jpeg\",\n" + 
				"	\"is_active\": false\n" + 
				"}");
		Response r = controller.createRide("{\n" + 
				"  \"aid\": 1,\n" + 
				"  \"location_info\": {\n" + 
				"    \"from_city\": \"Barrington\",\n" + 
				"    \"from_zip\": \"60010\",\n" + 
				"    \"to_city\": \"Milwaukee\",\n" + 
				"    \"to_zip\": \"53202\"\n" + 
				"  },\n" + 
				"  \"date_time\": {\n" + 
				"    \"date\": \"14-Apr-2020\",\n" + 
				"    \"time\": \"09:00\"\n" + 
				"  },\n" + 
				"  \"car_info\": {\n" + 
				"    \"make\": \"Audi\",\n" + 
				"    \"model\": \"A4\",\n" + 
				"    \"color\": \"Gray\",\n" + 
				"    \"plate_state\": \"IL\",\n" + 
				"    \"plate_serial\": \"COVID19\"\n" + 
				"  },\n" + 
				"  \"max_passengers\": 2,\n" + 
				"  \"amount_per_passenger\": 15.00,\n" + 
				"  \"conditions\": \"No more than one carry on per passenger. No pets.\"\n" + 
				"}");
		assertTrue(r.getStatus() == 400);
	}
	
	@Test
	void test_update_ride() {
		controller.createAccount("{\n" + 
				"	\"first_name\": \"John\",\n" + 
				"	\"last_name\": \"Smith\",\n" + 
				"	\"phone\": \"312-456-7890\",\n" + 
				"	\"picture\": \"http://example.com/images/john-smith.jpeg\",\n" + 
				"	\"is_active\": false\n" + 
				"}");
		controller.createRide("{\n" + 
				"  \"aid\": 1,\n" + 
				"  \"location_info\": {\n" + 
				"    \"from_city\": \"Barrington\",\n" + 
				"    \"from_zip\": \"60010\",\n" + 
				"    \"to_city\": \"Milwaukee\",\n" + 
				"    \"to_zip\": \"53202\"\n" + 
				"  },\n" + 
				"  \"date_time\": {\n" + 
				"    \"date\": \"14-Apr-2020\",\n" + 
				"    \"time\": \"09:00\"\n" + 
				"  },\n" + 
				"  \"car_info\": {\n" + 
				"    \"make\": \"Audi\",\n" + 
				"    \"model\": \"A4\",\n" + 
				"    \"color\": \"Gray\",\n" + 
				"    \"plate_state\": \"IL\",\n" + 
				"    \"plate_serial\": \"COVID19\"\n" + 
				"  },\n" + 
				"  \"max_passengers\": 2,\n" + 
				"  \"amount_per_passenger\": 15.00,\n" + 
				"  \"conditions\": \"No more than one carry on per passenger. No pets.\"\n" + 
				"}");
		
		Response r = controller.updateRide(1, "{\n" + 
				"  \"aid\": 1,\n" + 
				"  \"location_info\": {\n" + 
				"    \"from_city\": \"Barrington\",\n" + 
				"    \"from_zip\": \"60010\",\n" + 
				"    \"to_city\": \"Milwaukee\",\n" + 
				"    \"to_zip\": \"53202\"\n" + 
				"  },\n" + 
				"  \"date_time\": {\n" + 
				"    \"date\": \"14-Apr-2020\",\n" + 
				"    \"time\": \"09:00\"\n" + 
				"  },\n" + 
				"  \"car_info\": {\n" + 
				"    \"make\": \"Audi\",\n" + 
				"    \"model\": \"A4\",\n" + 
				"    \"color\": \"Gray\",\n" + 
				"    \"plate_state\": \"IL\",\n" + 
				"    \"plate_serial\": \"COVID19\"\n" + 
				"  },\n" + 
				"  \"max_passengers\": 2,\n" + 
				"  \"amount_per_passenger\": 15.00,\n" + 
				"  \"conditions\": \"No more than one carry on per passenger. No pets.\"\n" + 
				"}");
		assertTrue(r.getStatus() == 404);
		
	}
	
	@Test
	void test_get_ride_detail() {
		controller.createAccount("{\n" + 
				"	\"first_name\": \"John\",\n" + 
				"	\"last_name\": \"Smith\",\n" + 
				"	\"phone\": \"312-456-7890\",\n" + 
				"	\"picture\": \"http://example.com/images/john-smith.jpeg\",\n" + 
				"	\"is_active\": false\n" + 
				"}");
		controller.createRide("{\n" + 
				"  \"aid\": 1,\n" + 
				"  \"location_info\": {\n" + 
				"    \"from_city\": \"Barrington\",\n" + 
				"    \"from_zip\": \"60010\",\n" + 
				"    \"to_city\": \"Milwaukee\",\n" + 
				"    \"to_zip\": \"53202\"\n" + 
				"  },\n" + 
				"  \"date_time\": {\n" + 
				"    \"date\": \"14-Apr-2020\",\n" + 
				"    \"time\": \"09:00\"\n" + 
				"  },\n" + 
				"  \"car_info\": {\n" + 
				"    \"make\": \"Audi\",\n" + 
				"    \"model\": \"A4\",\n" + 
				"    \"color\": \"Gray\",\n" + 
				"    \"plate_state\": \"IL\",\n" + 
				"    \"plate_serial\": \"COVID19\"\n" + 
				"  },\n" + 
				"  \"max_passengers\": 2,\n" + 
				"  \"amount_per_passenger\": 15.00,\n" + 
				"  \"conditions\": \"No more than one carry on per passenger. No pets.\"\n" + 
				"}");
		
		Response r = controller.getRideDetail(1);
		assertTrue(r.getStatus() == 404);
	}
	
	@Test
	void test_get_messages() {
		Response r = controller.getMessages(1);
		assertTrue(r.getStatus() == 200);
	}
	
	@Test
	void test_get_reports() {
		Response r = controller.getReports();
		assertTrue(r.getStatus() == 200);
	}
	
	@Test
	void test_get_driver_ratings() {
		controller.createAccount("{\n" + 
				"	\"first_name\": \"John\",\n" + 
				"	\"last_name\": \"Smith\",\n" + 
				"	\"phone\": \"312-456-7890\",\n" + 
				"	\"picture\": \"http://example.com/images/john-smith.jpeg\",\n" + 
				"	\"is_active\": false\n" + 
				"}");
		Response r = controller.getDriverRatings(1);
		assertTrue(r.getStatus() == 200);
	}
	
	@Test
	void test_get_rider_ratings() {
		controller.createAccount("{\n" + 
				"	\"first_name\": \"John\",\n" + 
				"	\"last_name\": \"Smith\",\n" + 
				"	\"phone\": \"312-456-7890\",\n" + 
				"	\"picture\": \"http://example.com/images/john-smith.jpeg\",\n" + 
				"	\"is_active\": false\n" + 
				"}");
		Response r = controller.getRiderRatings(1);
		assertTrue(r.getStatus() == 200);
	}
	

}
