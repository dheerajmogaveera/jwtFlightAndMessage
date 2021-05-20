package com.cts.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {
	@Id
    @GeneratedValue
    @Column(name = "USER_ID")
	private int id; 
//	@Id
//	private int id; 
	@Column(nullable = false)
	private String fname; 
	private String lname; // @Column is not required because variable & column names are same
	@Column(unique = true,nullable = false)
	private String email;
	@Column(nullable = false)
	private String password;
	@Column(unique=true,nullable=false)
	private String mobileNumber;
	@OneToMany(mappedBy = "sender")
	private List<Message> senderMessage;
	@OneToMany(mappedBy = "receiver")
	private List<Message> receiverMessage;
	
public User() {
		
	}
	public User(String fname,String lname,String email,String password,String mobileNumber) {
	this.fname=fname;
	this.lname=lname;
	this.email=email;
	this.password=password;
	this.mobileNumber=mobileNumber;
	}
	
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public List<Message> getSenderMessage() {
		return senderMessage;
	}
	public void setSenderMessage(List<Message> senderMessage) {
		this.senderMessage = senderMessage;
	}
	public List<Message> getReceiverMessage() {
		return receiverMessage;
	}
	public void setReceiverMessage(List<Message> receiverMessage) {
		this.receiverMessage = receiverMessage;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", fname=" + fname + ", lname=" + lname + ", email=" + email + ", password="
				+ password + ", mobileNumber=" + mobileNumber + "]";
	}
	
}
