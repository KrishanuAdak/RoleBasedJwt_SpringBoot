package com.example.demo1.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo1.model.Client;

@Repository
public interface UserRepo extends JpaRepository<Client,Integer> {
	
	@Query(value="select * from users where email=?1",nativeQuery=true)
	public Client findByEmail(String email);

}
