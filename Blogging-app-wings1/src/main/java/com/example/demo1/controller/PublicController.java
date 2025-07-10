package com.example.demo1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo1.model.Client;
import com.example.demo1.service.UserService;
import com.example.demo1.util.JwtUtil;
import java.util.*;

@RestController
@RequestMapping("/api/public")
public class PublicController {
	@Autowired
	private JwtUtil jwtUtil;
		
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService service;
	
	@GetMapping("/home")
	public String home() {
		return "home";
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Client client){
		try {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(client.getEmail(),client.getPassword()));
		System.out.print("create Token"+client.getEmail());
		String token=jwtUtil.generateToken(client.getEmail());
		Map<String,Object> res=new HashMap<>();
		res.put("Token", token);
		res.put("Status", HttpStatus.OK.value());
		return ResponseEntity.status(HttpStatus.OK).body(res);
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		
		
		
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody Client c){
		if(c!=null)
		{
		Client cc=this.service.saveUser(c);
		return ResponseEntity.status(HttpStatus.CREATED).body(cc);
		}
		return ResponseEntity.badRequest().build();
		
	}

}
