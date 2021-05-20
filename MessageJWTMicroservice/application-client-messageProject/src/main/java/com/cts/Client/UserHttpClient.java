package com.cts.Client;


import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cts.model.JwtRequest;
import com.cts.model.Message;
import com.cts.model.User;


@FeignClient(url = "http://localhost:8082", name = "USER-MICRO")
public interface UserHttpClient {
    @GetMapping("/get-user-by-uname")
    public User getUserByUname(@RequestHeader(value = "Authorization", required = true) String authorizationHeader, @RequestParam String uname);

    @PostMapping("/login")
   	public ResponseEntity<?> loginForToken(@RequestBody JwtRequest authenticationRequest);
    
    @RequestMapping(value = "/register", method = RequestMethod.POST)
	public User register(@RequestBody User user);
    @GetMapping(value="/received")
	public List<Message> getAllReceivedMessage(@RequestHeader(value = "Authorization", required = true) String authorizationHeader, @RequestParam int receivedId);

	@GetMapping(value="/sender")
	public List<Message> getAllSenderMessage(@RequestHeader(value = "Authorization", required = true) String authorizationHeader, @RequestParam int senderId);
	
	@PostMapping(value="/sendMessage")
	public Message addUserMessage(@RequestHeader(value = "Authorization", required = true) String authorizationHeader,@RequestBody Message m);
   
}