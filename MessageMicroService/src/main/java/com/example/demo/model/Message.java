package com.example.demo.model;


import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Message {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String description;
	@CreationTimestamp
	private LocalDateTime dateTime;
	private int receiverid;
	private int senderid;
	
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
	
	public int getReceiverid() {
		return receiverid;
	}
	public void setReceiverid(int receiverid) {
		this.receiverid = receiverid;
	}
	public int getSenderid() {
		return senderid;
	}
	public void setSenderid(int senderid) {
		this.senderid = senderid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Message(){}
	
	
	public Message(String description, int receiverid) {
		super();
		this.description = description;
		this.receiverid = receiverid;
	}
	public Message(String description, int receiverid, int senderid) {
		this.description = description;
		this.receiverid = receiverid;
		this.senderid = senderid;
	}
	@Override
	public String toString() {
		return "Message [id=" + id + ", description=" + description + ", dateTime=" + dateTime + ", receiver_id="
				+ receiverid + ", sender_id=" + senderid + "]";
	}
	
	
	
}
