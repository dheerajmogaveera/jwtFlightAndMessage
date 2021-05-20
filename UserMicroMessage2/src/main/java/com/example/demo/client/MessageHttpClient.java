package com.example.demo.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.vo.Message;



@FeignClient(url = "http://localhost:8081", name = "MESSAGE")
public interface MessageHttpClient {
	@GetMapping(value="/received")
	public List<Message> getAllReceivedMessage(@RequestParam int receivedId);

	@GetMapping(value="/sender")
	public List<Message> getAllSenderMessage(@RequestParam int senderId);
	
	@PostMapping(value="/sendMessage")
	public Message addUserMessage(@RequestBody Message m);
}