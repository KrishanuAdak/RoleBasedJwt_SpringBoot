package com.example.demo1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo1.model.Client;
import com.example.demo1.repo.UserRepo;

@Service
public class UserService {
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepo repo;
	public Client saveUser(Client client) {
		Client c=client;
		if(c!=null) {
			c.setEmail(client.getEmail());
			c.setPassword(passwordEncoder.encode(client.getPassword()));
			if(c.getEmail().contains("manager"))
			{
			c.setRoles("MANAGER");
			}
			else {
				c.setRoles("EMPLOYEE");
			}
			Client x=this.repo.save(c);
			return x;
		}
		return null;
		
		
	}

}
