package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.JwtRequest;
import com.example.demo.JwtResponse;
import com.example.demo.JwtUtil;
import com.example.demo.model.Booking;
import com.example.demo.model.Flight;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

@RestController
public class MainController {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtUtil jwtTokenUtil;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	UserService us;
	@Autowired 
	UserRepository ur;

	@GetMapping("/")
	public String hello() {
		return "Hello World";
	}
	@GetMapping("/get-user-by-uname")
	public User hello(@RequestParam String uname) {
		return ur.findByUname(uname);
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(new JwtResponse(token));
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public User register(@RequestBody User user) {
		us.register(user);
		return user;
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public Flight searchFlight(@RequestBody Flight flight) {

		Flight fl = us.searchFlights(flight.getSource(), flight.getDestination());
		return fl;

	}

	@RequestMapping(value = "/book", method = RequestMethod.POST)
	public Booking bookTicket(@RequestParam int flightId, @RequestParam int noOfSeats, @RequestParam int userId) {
		System.out.println("CONSOLED--------------------------" + flightId + " " + noOfSeats + " " + userId);
		User loginuser = us.getUSerById(userId);
		System.out.println("USer " + loginuser);
		Booking book = us.bookTicket(loginuser, flightId, noOfSeats);
		return book;

	}

	@RequestMapping(value = "/cancelFlight", method = RequestMethod.POST)
	public String cancelBooking(@RequestParam int bookingId) {
		try {
			us.cancelBooking(bookingId);
			return "{\"message\":\"Booking Canceled\"}";
		} catch (Exception e) {
			// TODO: handle exception
			return "{\"message\":\"Error Cancelling\", \"err\":"+e.getMessage()+"}";
		}
		
	}
}
