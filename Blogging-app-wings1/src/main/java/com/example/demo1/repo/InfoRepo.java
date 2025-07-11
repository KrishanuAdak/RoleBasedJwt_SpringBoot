package com.example.demo1.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo1.model.Information;

@Repository
public interface InfoRepo extends JpaRepository<Information,Integer>{
	
	@Query(value="select * from info where manager_id=?1",nativeQuery=true)
	public List<Information> showMessagesByManagerId(int id);

}
