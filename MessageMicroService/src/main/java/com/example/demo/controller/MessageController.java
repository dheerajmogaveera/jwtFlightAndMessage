package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Message;
import com.example.demo.repository.MessageRepository;
import com.example.demo.service.MessageService;

@RestController
public class MessageController {

	@Autowired 
	MessageService ms;
	@Autowired 
	MessageRepository mr;
	
	@GetMapping(value="/received")
	public List<Message> getAllReceivedMessage(@RequestParam int receivedId)
	{
		return ms.inbox(receivedId);
	}
	@GetMapping(value="/sender")
	public List<Message> getAllSenderMessage(@RequestParam int senderId)
	{
		return ms.sent(senderId);
	}
	
	@PostMapping(value="/sendMessage")
	public Message addUserMessage(@RequestBody Message m) {
		return ms.sendMessage(m);
	}
}
