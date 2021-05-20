package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.client.UserHttpClient;
import com.example.demo.model.Booking;
import com.example.demo.model.Flight;
import com.example.demo.model.User;
import com.example.demo.vo.JwtRequest;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service
public class UserService {
	
	
	@Autowired
	private UserHttpClient client;

	public String  login(String username, String password) {

		JwtRequest authenticationRequest = new JwtRequest(username, password);
		JsonParser parser = new JsonParser();
    	String s = client.loginForToken(authenticationRequest).getBody().toString();
    	System.out.println(s);
    	JsonElement jsonTree = parser.parse(s);
    	
    	JsonObject jsonObject = jsonTree.getAsJsonObject();
    	String authorizationHeader = "Bearer "+jsonObject.get("token").getAsString();
    	return authorizationHeader;
    	

	}

	public User register(User u) {
		//System.out.println("Ur value:" + ur);
		u.setRole("ROLE_user");
	    User user=client.register(u);
		System.out.println("User successfully registered");
		return user;

	}

	public Flight searchFlights(String source, String destination, String token) {
		String uri = "http://localhost:8081/flights";
//		ResponseEntity<Flight[]> response = restTemplate.getForEntity(uri, Flight[].class);
//		Flight[] f = response.getBody();
//		for (Flight fi : f) {
//			if (fi.getSource().equalsIgnoreCase(source) && fi.getDestination().equalsIgnoreCase(destination)) {
//				return fi;
//			}
//		}
		Flight fli = new Flight();
		fli.setSource(source);
		fli.setDestination(destination);
		Flight returnedFlight = client.searchFlights(token, fli);
		return returnedFlight;
	}
	
	public Booking bookTicket(int uId,int count,int id,String token) {
		
		uId = 3;
		Booking b = client.bookTicket(token,id, count, uId);
		
		return b;
		
	}
	public String cancelBooking(int id, String token) {
		try {
			return client.cancelBooking(token, id);
		}
		catch (IndexOutOfBoundsException e) {
			System.out.println("No such booking id");
			return "Not cancelled";
		}
	}

}
