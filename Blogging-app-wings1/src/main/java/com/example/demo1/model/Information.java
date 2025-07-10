package com.example.demo1.model;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="info")
public class Information {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	private String messages;
	private LocalDate published_Date;
	private LocalTime published_time;
	@ManyToOne
	@JoinColumn(name="Manager_Id",referencedColumnName="id")
	private Client client;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMessages() {
		return messages;
	}
	public void setMessages(String messages) {
		this.messages = messages;
	}
	public LocalDate getPublished_Date() {
		return published_Date;
	}
	public void setPublished_Date(LocalDate published_Date) {
		this.published_Date = published_Date;
	}
	public LocalTime getPublished_time() {
		return published_time;
	}
	public void setPublished_time(LocalTime published_time) {
		this.published_time = published_time;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Information(int id, String messages, LocalDate published_Date, LocalTime published_time, Client client) {
		super();
		this.id = id;
		this.messages = messages;
		this.published_Date = published_Date;
		this.published_time = published_time;
		this.client = client;
	}
	public Information() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
