package com.example.demo1.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo1.model.Client;
import com.example.demo1.model.Information;
import com.example.demo1.repo.InfoRepo;
import com.example.demo1.repo.UserRepo;

@RestController
@RequestMapping("/api/auth/manager")
@PreAuthorize("hasRole('MANAGER')")
public class ManagerController {
	
	@Autowired
	private UserRepo repo;
	
	@Autowired
	private InfoRepo infoRepo; 
	
	@GetMapping("/home")
	public String home() {
		return "Manager home";
	}
	
	
	@PostMapping("/post/info")
	public ResponseEntity<?> postInfos(Principal principal,@RequestBody Information info){
		String name=principal.getName();
		Client cc=this.repo.findByEmail(name);
		if(info!=null) {
			Information i=info; 
			i.setMessages(info.getMessages());
			i.setPublished_Date(LocalDate.now());
			i.setPublished_time(LocalTime.now());
			i.setClient(cc);
			Information x=this.infoRepo.save(i);
			return ResponseEntity.status(HttpStatus.CREATED).body(x);
			
			
		}
		return ResponseEntity.badRequest().build();
		
		
	}
	
	@GetMapping("/info")
	public ResponseEntity<?> showMessagesById(Principal principal){
		String name=principal.getName();
		Client cc=this.repo.findByEmail(name);
		List<Information> lists=this.infoRepo.showMessagesByManagerId(cc.getId());
		System.out.print(cc.getId() +" "+lists.size());
		if(lists.size()>0) {
			return ResponseEntity.status(HttpStatus.OK).body(lists);
		}
		return ResponseEntity.badRequest().build();
		
		
		
		
	}
	

}
