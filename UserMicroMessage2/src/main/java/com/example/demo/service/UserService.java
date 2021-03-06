package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.client.MessageHttpClient;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.vo.Message;

@Service
public class UserService {
	@Autowired
    private UserRepository ur;

	@Autowired 
	private MessageHttpClient client;
	
	public User getUSerById(int id) {
		return ur.getOne(id);
	}
	public User login(String uname, String password) {

		User u = ur.findByUname(uname);
		if (u == null) {
			System.out.println("No such email registered");
			return null;
		}
		if (u.getPassword().equals(password)) {
			System.out.println("Welcome " + u.getFname());
			System.out.println("You have successfully logged in");
		} else {
			System.out.println("Incorrect password");
			return null;
		}
		return u;

	}

	public void register(User u) {
		System.out.println("Ur value:" + ur);
		ur.save(u);
		System.out.println("User successfully registered");

	}
	
	public List<Message> inboxMessages(int receiverId){
		return client.getAllReceivedMessage(receiverId);
	}
	
	public List<Message> sentMessages(int senderId){
		return client.getAllSenderMessage(senderId);
	}
	
	public Message sendTheMessage(Message m) {
		return client.addUserMessage(m);
	}

	
}
