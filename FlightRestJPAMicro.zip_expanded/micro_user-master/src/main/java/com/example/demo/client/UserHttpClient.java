package com.example.demo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Booking;
import com.example.demo.model.Flight;
import com.example.demo.model.User;
import com.example.demo.vo.JwtRequest;


@FeignClient(url = "http://localhost:8081", name = "USER-MICRO")
public interface UserHttpClient {
    @GetMapping("/")
    public String getHi(@RequestHeader(value = "Authorization", required = true) String authorizationHeader);
    @GetMapping("/get-user-by-uname")
    public User getUserByUname(@RequestHeader(value = "Authorization", required = true) String authorizationHeader, @RequestParam String uname);
    @PostMapping("/login")
   	public ResponseEntity<?> loginForToken(@RequestBody JwtRequest authenticationRequest);
    
    @RequestMapping(value = "/register", method = RequestMethod.POST)
	public User register(@RequestBody User user);
    
    @PostMapping(value="/search")
	public Flight searchFlights(@RequestHeader(value = "Authorization", required = true) String authorizationHeader, @RequestBody Flight f);
    
    @RequestMapping(value = "/book", method = RequestMethod.POST)
	public Booking bookTicket(@RequestHeader(value = "Authorization", required = true) String authorizationHeader, @RequestParam int flightId, @RequestParam int noOfSeats, @RequestParam int userId);

    @RequestMapping(value = "/cancelFlight", method = RequestMethod.POST)
	public String cancelBooking(@RequestHeader(value = "Authorization", required = true) String authorizationHeader,@RequestParam int bookingId);
}