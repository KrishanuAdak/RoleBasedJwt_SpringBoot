package com.example.demo1.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class Employee_Manager_Mapping {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(referencedColumnName="employee_id",name="Employee_Id")
	private Employee employee;
	
	@ManyToOne
	@JoinColumn(referencedColumnName="manager_id",name="Manager_Id")
	private Manager manager;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public Employee_Manager_Mapping(int id, Employee employee, Manager manager) {
		super();
		this.id = id;
		this.employee = employee;
		this.manager = manager;
	}

	public Employee_Manager_Mapping() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
