package main;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;


import javax.annotation.PostConstruct;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("/sar")
public class REST_controller {
	
	private RideManager rm = new RideManager();
	private AccountManager am = new AccountManager();
	private ReportManager pm = new ReportManager();
	
	
	@Path("/accounts")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createAccount(String JSONString) {
		JsonObject jobj = new Gson().fromJson(JSONString, JsonObject.class);
		String fn = jobj.get("first_name").getAsString();
		String ln = jobj.get("last_name").getAsString();
		String ph = jobj.get("phone").getAsString();
		String pic = jobj.get("picture").getAsString();
		Boolean isActive = jobj.get("is_active").getAsBoolean();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		if(ph.contains("O")) {
			ErrorResponse error = new ErrorResponse("Invalid phone number", "/accounts");
			return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson(error)).build();
		}
        String s = gson.toJson(am.createAccount(fn, ln, ph, pic, isActive));
		return Response.status(Response.Status.CREATED).entity(s).build();
	}
	

	@Path("/accounts/{aid}/status")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response activateAccount(@PathParam("aid") int aid, String JSONString) {
		JsonObject jobj = new Gson().fromJson(JSONString, JsonObject.class);
		boolean isActive = jobj.get("is_active").getAsBoolean();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		if(isActive != true && isActive != false) {
			ErrorResponse error = new ErrorResponse("Invalid value for is_active", "/accounts/" + aid + "/status");
			return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson(error)).build();
		}
		int success = am.activateAccount(aid, isActive);
		if(success == 0) {
			return Response.status(Response.Status.NOT_FOUND).entity("The account identified by aid " + aid + " does not exist.").build();
		}
		return Response.status(Response.Status.NO_CONTENT).build();
		
	}
	
	@Path("/accounts/{aid}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateAccount(@PathParam("aid") int aid, String JSONString) {
		JsonObject jobj = new Gson().fromJson(JSONString, JsonObject.class);
		String fn = jobj.get("first_name").getAsString();
		String ln = jobj.get("last_name").getAsString();
		String ph = jobj.get("phone").getAsString();
		String pic = jobj.get("picture").getAsString();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		if(fn == null || ln == null) {
			ErrorResponse error = new ErrorResponse("First name and last name are required fields.", "/accounts");
			return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson(error)).build();
		}
		int success = am.updateAccount(aid, fn, ln, ph, pic);
		if(success == 0) {
			return Response.status(Response.Status.NOT_FOUND).entity("The account identified by aid " + aid + " does not exist.").build();
		}
		return Response.status(Response.Status.NO_CONTENT).build();
	}
	
	@Path("/accounts/{aid}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteAccount(@PathParam("aid") int aid) {
		int success = am.deleteAccount(aid);
		if(success == 0) {
			return Response.status(Response.Status.NOT_FOUND).entity("The account identified by aid " + aid + " does not exist.").build();
		}
		return Response.status(Response.Status.NO_CONTENT).entity("").build();
	}
	
	@Path("/accounts")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAccounts(@Context UriInfo uriInfo) {
		String keyword = uriInfo.getQueryParameters().getFirst("key");
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String s;
		if(keyword == "" || keyword == null) {
			s = gson.toJson(am.viewAllAccounts());
		}
		else {
			s = gson.toJson(am.searchAccounts(keyword));
		}
		return Response.status(Response.Status.OK).entity(s).build();
	}
	
	@Path("/accounts/{aid}/ratings")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response rateAccount(@PathParam("aid") int aid, String JSONString, @Context UriInfo uriInfo) {
		JsonObject jobj = new Gson().fromJson(JSONString, JsonObject.class);
		int rid = jobj.get("rid").getAsInt();
		int sent_by_id = jobj.get("sent_by_id").getAsInt();
		int rating = jobj.get("rating").getAsInt();
		String comment = jobj.get("comment").getAsString();

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Rating r = am.rateAccount(aid, rid, sent_by_id, rating, comment);
		if(r == null) {
			ErrorResponse error = new ErrorResponse("This account (" + sent_by_id + ") didn't create this ride (" + rid +") nor was it a passenger", "/accounts/" + aid + "/ratings");
			return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson(error)).build();
		}
		String s = gson.toJson(r);
		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		builder.path("accounts/" + aid + "/ratings/" + r.getSid());
		return Response.created(builder.build()).entity(s).build();
	}
	
	@Path("/accounts/{aid}/driver")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDriverRatings(@PathParam("aid") int aid) { 
		Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
		String s = gson.toJson(am.viewDriverRatings(aid));
		JsonObject jobj = gson.fromJson(s, JsonObject.class);
		jobj.remove("name");
		jobj.remove("last_name");
		jobj.remove("phone");
		jobj.remove("picture");
		jobj.remove("date_created");
		jobj.remove("is_active");
		jobj.remove("driverRatings");
		jobj.remove("riderRatings");
		s = gson.toJson(jobj);
		return Response.status(Response.Status.OK).entity(s).build();
	}
	
	@Path("/accounts/{aid}/rider")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRiderRatings(@PathParam("aid") int aid) { 
		Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
		String s = gson.toJson(am.viewRiderRatings(aid));
		JsonObject jobj = gson.fromJson(s, JsonObject.class);
		jobj.remove("name");
		jobj.remove("last_name");
		jobj.remove("phone");
		jobj.remove("picture");
		jobj.remove("date_created");
		jobj.remove("is_active");
		jobj.remove("driverRatings");
		jobj.remove("riderRatings");
		s = gson.toJson(jobj);
		return Response.status(Response.Status.OK).entity(s).build();
	}
	
	
	
	
	
	
	
	
	@Path("/rides")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createRide(String JSONString) {
		JsonObject jobj = new Gson().fromJson(JSONString, JsonObject.class);
		int aid = jobj.get("aid").getAsInt();
		JsonObject locationInfo = jobj.getAsJsonObject("location_info");
		LocationInfo li = new LocationInfo(locationInfo.get("from_city").getAsString(),
				locationInfo.get("from_zip").getAsString(),
				locationInfo.get("to_city").getAsString(),
				locationInfo.get("to_zip").getAsString());
		JsonObject dateTime = jobj.getAsJsonObject("date_time");
		DateTime dt = new DateTime(dateTime.get("date").getAsString(), dateTime.get("time").getAsString());
		JsonObject carInfo = jobj.getAsJsonObject("car_info");
		Car car = new Car(carInfo.get("make").getAsString(), carInfo.get("model").getAsString(), carInfo.get("color").getAsString(),
				carInfo.get("plate_state").getAsString(), carInfo.get("plate_serial").getAsString());
		int maxPassengers = jobj.get("max_passengers").getAsInt();
		JsonElement amountPerPassengerJSON = jobj.get("amount_per_passenger");
		Double amountPerPassenger;
		if(amountPerPassengerJSON.isJsonNull()) {
			amountPerPassenger = null;
		}
		else {
			amountPerPassenger = jobj.get("amount_per_passenger").getAsDouble();
		}
		String conditions = jobj.get("conditions").getAsString();

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Ride r = rm.createRide(aid, li, dt, car, maxPassengers, amountPerPassenger, conditions);
		if(r == null) {
			ErrorResponse error = new ErrorResponse("This account (" + aid + ") is not active, may not create a ride.", "/rides");
			return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson(error)).build();
		}
		String s = gson.toJson(r);
		return Response.status(Response.Status.CREATED).entity(s).build();
		
	}
	
	@Path("/rides/{rid}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateRide(@PathParam("rid") int rid, String JSONString) {
		JsonObject jobj = new Gson().fromJson(JSONString, JsonObject.class);
		int aid = jobj.get("aid").getAsInt();
		JsonObject locationInfo = jobj.getAsJsonObject("location_info");
		LocationInfo li = new LocationInfo(locationInfo.get("from_city").getAsString(),
				locationInfo.get("from_zip").getAsString(),
				locationInfo.get("to_city").getAsString(),
				locationInfo.get("to_zip").getAsString());
		JsonObject dateTime = jobj.getAsJsonObject("date_time");
		DateTime dt = new DateTime(dateTime.get("date").getAsString(), dateTime.get("time").getAsString());
		JsonObject carInfo = jobj.getAsJsonObject("car_info");
		Car car = new Car(carInfo.get("make").getAsString(), carInfo.get("model").getAsString(), carInfo.get("color").getAsString(),
				carInfo.get("plate_state").getAsString(), carInfo.get("plate_serial").getAsString());
		int maxPassengers = jobj.get("max_passengers").getAsInt();
		double amountPerPassenger = jobj.get("amount_per_passenger").getAsDouble();
		String conditions = jobj.get("conditions").getAsString();
		int success = rm.updateRide(rid, aid, li, dt, car, maxPassengers, amountPerPassenger, conditions);
		if(success == 0) {
			return Response.status(Response.Status.NOT_FOUND).entity("The ride identified by rid " + rid + " does not exist.").build();
		}
		return Response.status(Response.Status.NO_CONTENT).build();
	}
	
	@Path("/rides/{rid}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteRide(@PathParam("rid") int rid) {
		int success = rm.deleteRide(rid);
		if(success == 0) {
			return Response.status(Response.Status.NOT_FOUND).entity("The ride identified by rid " + rid + " does not exist.").build();
		}
		return Response.status(Response.Status.NO_CONTENT).build();
	}
	
	@Path("/rides")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRides(@Context UriInfo uriInfo) {
		String from = uriInfo.getQueryParameters().getFirst("from");
		String to = uriInfo.getQueryParameters().getFirst("to");
		String date = uriInfo.getQueryParameters().getFirst("date");
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String s;
		if(from != null || to != null || date != null) {
			s = gson.toJson(rm.searchRides(from, to, date));
		}
		else {
			s = gson.toJson(rm.getRides());
		}
		return Response.status(Response.Status.OK).entity(s).build();
	}
	
	@Path("/rides/{rid}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRideDetail(@PathParam("rid") int rid) {
		Ride r = rm.getRideDetail(rid);
		if(r == null) {
			return Response.status(Response.Status.NOT_FOUND).entity("No rides found.").build();
		}
		Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
		
		String s = gson.toJson(r);
		JsonObject jobj = gson.fromJson(s, JsonObject.class);
		jobj.remove("aid");
		jobj.getAsJsonObject("location_info").remove("count");
		jobj.remove("joinRequests");
		jobj.remove("messages");
		s = gson.toJson(jobj);
		return Response.status(Response.Status.OK).entity(s).build();
	}
	
	@Path("/rides/{rid}/join_requests")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createJoinRequest(@PathParam("rid") int rid, String JSONString, @Context UriInfo uriInfo) {
		JsonObject jobj = new Gson().fromJson(JSONString, JsonObject.class);
		int aid = jobj.get("aid").getAsInt();
		int passengers = jobj.get("passengers").getAsInt();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JoinRequest jr = rm.createJoinRequest(rid, aid, passengers);
		if(jr == null) {
			ErrorResponse error = new ErrorResponse("This account (" + aid + ") is not active, may not create a join ride request.", "/rides/" + rid + "/join_requests");
			return Response.status(Response.Status.BAD_REQUEST).entity(gson.toJson(error)).build();
		}
		String s = gson.toJson(jr);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path("rides/" + rid + "/join_requests/" + jr.getJid());

        return Response.created(builder.build()).entity(s).build();
        
		//return Response.status(Response.Status.CREATED).entity(s).build();
	}
	
	@Path("/rides/{rid}/join_requests/{jid}")
	@PATCH
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON) 
	public Response confirmJoinRequest(@PathParam("rid") int rid, @PathParam("jid") int jid, String JSONString ) {
		JsonObject jobj = new Gson().fromJson(JSONString, JsonObject.class);
		int success = 0;
		if(jobj.has("pickip_confirmed")) {
			boolean pickup_confirmed = jobj.get("pickup_confirmed").getAsBoolean();
			success = rm.confirmPickup(rid, jid, pickup_confirmed);
		}
		if(jobj.has("ride_confirmed")) {
			boolean ride_confirmed = jobj.get("ride_confirmed").getAsBoolean();
			success = rm.confirmRideRequest(rid, jid, ride_confirmed);
		}
		return Response.status(Response.Status.OK).entity("").build();
	}
	
	@Path("/rides/{rid}/messages") 
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addMessage(@PathParam("rid") int rid, String JSONString, @Context UriInfo uriInfo) {
		JsonObject jobj = new Gson().fromJson(JSONString, JsonObject.class);
		int aid = jobj.get("aid").getAsInt();
		String msg = jobj.get("msg").getAsString();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		Message m = rm.addMessage(rid, aid, msg);
		String s = gson.toJson(m);
		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		builder.path("rides/" + rid + "/messages/" + m.getMid());
		return Response.created(builder.build()).entity(s).build();
	}
	
	@Path("/rides/{rid}/messages")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMessages(@PathParam("rid") int rid) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String s = gson.toJson(rm.getMessages(rid));
		return Response.status(Response.Status.OK).entity(s).build();
	}
	
	
	
	@Path("/reports")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getReports() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String s = gson.toJson(pm.viewAllReports());
		return Response.status(Response.Status.OK).entity(s).build();
	}
	
	@Path("/reports/{pid}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getReport(@PathParam("pid") int pid, @Context UriInfo uriInfo) {
		String from = uriInfo.getQueryParameters().getFirst("start_date");
		String to = uriInfo.getQueryParameters().getFirst("end_date");
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		if(from == null) {
			String s = gson.toJson(pm.getReport(pid, "", ""));
			return Response.status(Response.Status.OK).entity(s).build();
		}
		String s = gson.toJson(pm.getReport(pid, from, to));
		return Response.status(Response.Status.OK).entity(s).build();
		
	}
	
	
	@PostConstruct
    public void postConstruct() {
        // This method gets executed exactly once, after the servlet container has been created
        // A good place to place code that needs to be executed once, at startup
    }
	
}

