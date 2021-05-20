package com.example.json.model;

public class UserVo {

	
	private String username;
	private String password;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UserVo(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public UserVo() {
		super();
	}
	
	
}
