package com.cts.Services;

import java.util.List;
//import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.model.Message;
import com.cts.model.User;
import com.cts.repository.UserRepository;

@Service
public class UserService{
//	Scanner sc=new Scanner(System.in);
	 	@Autowired
	    private UserRepository ur;
	 	
	    
		public User login(String mobileNumber,String password) {
			
			User u=ur.findByMobileNumber(mobileNumber);
			if(u==null)
			{
				System.out.println("No such mobile registered");
				return null;
			}
			if(u.getPassword().equals(password))
			{
				System.out.println("Welcome "+u.getFname());
				System.out.println("You have successfully logged in");
			}
			else
			{
				System.out.println("Incorrect password");
				return null;
			}
			
			return u;
		}

		public void register(User u) {
			System.out.println("Ur value:"+ur);
			ur.save(u);
			System.out.println("User successfully registered");
			
		}
		
		public String inbox(User u){
//			User u=ur.getOne(id);
			System.out.println(u);
			String result="<li>";
			for(Message m:u.getReceiverMessage()) {
				result+=m.getDescription()+"</li><li>";
			}
			result+="</li>";
			return result;
		}
		
		public String sentMessage(User u){
//			User u=ur.getOne(id);
			System.out.println(u);
			String result="<li>";
			for(Message m:u.getSenderMessage()) {
				result+=m.getDescription()+"</li><li>";
			}
			result+="</li>";
			return result;
		}
		
		
		
}
