package com.cts.model;

import java.time.LocalDateTime;


public class User {
	private int id;
	private String fname;
	private String lname; // @Column is not required because variable & column names are same
	private String uname;
	private String password;
	private String mobileNumber;
    private LocalDateTime localDateTime;
    private final String role="ROLE_user";
	public User() {

	}
	public String getRole() {
		return role;
	}
	public User(String fname, String lname, String uname, String password, String mobileNumber) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.uname = uname;
		this.password = password;
		this.mobileNumber = mobileNumber;
		
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
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}
	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", fname=" + fname + ", lname=" + lname + ", uname=" + uname + ", password="
				+ password + ", mobileNumber=" + mobileNumber + ", localDateTime=" + localDateTime + "]";
	}

	

	
}
