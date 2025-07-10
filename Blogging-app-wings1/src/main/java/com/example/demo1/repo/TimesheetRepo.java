package com.example.demo1.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo1.model.Timesheet;

@Repository
public interface TimesheetRepo extends JpaRepository<Timesheet,Integer>{

}
