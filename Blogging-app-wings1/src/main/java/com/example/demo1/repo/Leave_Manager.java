package com.example.demo1.repo;

import com.example.demo1.model.Client;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Leave_Manager")
public class Leave_Manager {
	
	private int id;
	private String leave_status;
	@ManyToOne
	@JoinColumn ( referencedColumnName="id",name="Manager_id")
	private Client client;

}
