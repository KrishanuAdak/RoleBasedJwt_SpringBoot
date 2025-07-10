package com.example.demo1.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo1.model.Client;
import com.example.demo1.model.Information;
import com.example.demo1.model.Timesheet;
import com.example.demo1.repo.InfoRepo;
import com.example.demo1.repo.TimesheetRepo;
import com.example.demo1.repo.UserRepo;
import java.util.*;

@RestController
@RequestMapping("/api/auth/employee")
public class EmployeeController {
	
	@Autowired
	private TimesheetRepo timerepo;
	
	@Autowired
	private InfoRepo repo1;
	
	@Autowired
	private UserRepo repo;
	
	@GetMapping("/home")
	public String home() {
		return "Employee forum";
	}
	
	@PostMapping("/fill/timesheet")
	public ResponseEntity<?> fillTimesheet(Principal principal,@RequestBody Timesheet timesheet){
		String name=principal.getName();
		Client cc=this.repo.findByEmail(name);
		if(timesheet!=null) {
			Timesheet x=new Timesheet();
			x.setClient(cc);
			x.setFilled_date(LocalDate.now());
			x.setFilled_time(LocalTime.now());
			x.setHours(9);
			Timesheet tt=this.timerepo.save(x);
			Map<String,Object> res=new HashMap<>();
			res.put("message", "Timesheet has been saved");
			res.put("Details", tt);
			return ResponseEntity.status(HttpStatus.CREATED).body(res);
			
		}
		return ResponseEntity.badRequest().build();
		
		
	}
	
	@GetMapping("/messages")
	public ResponseEntity<?> showMessagesByManager(){
		List<Information> lists=this.repo1.findAll();
		if(lists.size()>0) {
			return ResponseEntity.status(HttpStatus.OK).body(lists);
		}
		return ResponseEntity.badRequest().build();
		
		
	}
	
	

}
