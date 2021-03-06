package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Message;
import com.example.demo.repository.MessageRepository;

@Service
public class MessageService{
//	Scanner sc=new Scanner(System.in);
	 	@Autowired
	    private MessageRepository mr;

		public List<Message> inbox(int id){
			return mr.findByReceiverid(id);
		}
		
		public List<Message> sent(int id){
			return mr.findBySenderid(id);
		}
		
		public Message sendMessage(Message m) {
			mr.save(m);
			return m;
		}
		
		
		
}
