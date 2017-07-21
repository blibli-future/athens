package com.blibli.future.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blibli.future.model.YearlyLeave;

@Repository
public interface YearlyLeaveRepository extends JpaRepository<YearlyLeave,String>{

}
