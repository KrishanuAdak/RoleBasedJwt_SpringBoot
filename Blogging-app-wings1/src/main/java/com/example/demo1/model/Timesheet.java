package com.example.demo1.model;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="timesheet")
public class Timesheet {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int hours;
	private LocalDate filled_date;
	private LocalTime filled_time;
	@ManyToOne
	@JoinColumn(name="employee_id",referencedColumnName="id")
	Client client;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getHours() {
		return hours;
	}
	public void setHours(int hours) {
		this.hours = hours;
	}
	public LocalDate getFilled_date() {
		return filled_date;
	}
	public void setFilled_date(LocalDate filled_date) {
		this.filled_date = filled_date;
	}
	public LocalTime getFilled_time() {
		return filled_time;
	}
	public void setFilled_time(LocalTime filled_time) {
		this.filled_time = filled_time;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Timesheet(int id, int hours, LocalDate filled_date, LocalTime filled_time, Client client) {
		super();
		this.id = id;
		this.hours = hours;
		this.filled_date = filled_date;
		this.filled_time = filled_time;
		this.client = client;
	}
	public Timesheet() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
