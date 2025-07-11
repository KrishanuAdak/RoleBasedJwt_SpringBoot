package com.example.demo1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.util.*;

@Entity
@Table(name="Leave_applied")
public class Leave_apply {
   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   private int id;
   private Date leave_from;
   private Date leave_till;
   @ManyToOne
   @JoinColumn(referencedColumnName="id",name="Employee_id")
   private Client client;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public Date getLeave_from() {
	return leave_from;
}
public void setLeave_from(Date leave_from) {
	this.leave_from = leave_from;
}
public Date getLeave_till() {
	return leave_till;
}
public void setLeave_till(Date leave_till) {
	this.leave_till = leave_till;
}
public Client getClient() {
	return client;
}
public void setClient(Client client) {
	this.client = client;
}
public Leave_apply() {
	super();
	// TODO Auto-generated constructor stub
}
public Leave_apply(int id, Date leave_from, Date leave_till, Client client) {
	super();
	this.id = id;
	this.leave_from = leave_from;
	this.leave_till = leave_till;
	this.client = client;
}
   
   
   
   
}
