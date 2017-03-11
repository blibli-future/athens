package com.blibli.future.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blibli.future.model.Leave;


@Repository
public interface LeaveRepository extends JpaRepository<Leave, String>{

}
