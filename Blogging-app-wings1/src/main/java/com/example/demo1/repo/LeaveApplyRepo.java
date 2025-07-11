package com.example.demo1.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo1.model.Leave_apply;
@Repository
public interface LeaveApplyRepo extends JpaRepository<Leave_apply,Integer>{

}
