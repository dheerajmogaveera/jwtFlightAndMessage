package com.cts.Services;

import java.util.List;
import java.util.Scanner;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.model.Message;
import com.cts.model.User;
import com.cts.repository.MessageRepository;
import com.cts.repository.UserRepository;

@Service
public class MessageService {
	@Autowired
    private MessageRepository mr;
	@Autowired
    private UserRepository ur;
	
	public String sendMessage(String mobileNumber,String message,User u){

		Message m=new Message();
		m.setDescription(message);
		User u1=ur.findByMobileNumber(mobileNumber);
		m.setReceiver(u1);
		m.setSender(u);
		mr.save(m);
		return "message send successfully";
	}

}
