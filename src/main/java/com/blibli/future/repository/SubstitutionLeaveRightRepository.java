package com.blibli.future.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blibli.future.model.SubstitutionLeaveRight;

@Repository
public interface SubstitutionLeaveRightRepository extends JpaRepository<SubstitutionLeaveRight,String>{

}
