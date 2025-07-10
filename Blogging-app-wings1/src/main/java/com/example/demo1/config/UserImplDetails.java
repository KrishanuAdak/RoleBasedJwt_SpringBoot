package com.example.demo1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.demo1.model.Client;
import com.example.demo1.repo.UserRepo;

@Component
public class UserImplDetails implements UserDetailsService {
	@Autowired
	private UserRepo repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Client c=this.repo.findByEmail(username);
		if(c==null) {
		throw new UsernameNotFoundException("Username not found");
		}
		return User.builder().username(c.getEmail()).password(c.getPassword()).roles(c.getRoles()).build();
	}

}
