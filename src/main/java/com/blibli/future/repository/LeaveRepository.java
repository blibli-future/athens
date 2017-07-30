package com.blibli.future.repository;

import com.blibli.future.model.Leave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveRepository extends JpaRepository<Leave, String> {
	List<Leave> findByGenderAndMaritalStatusAndReligion(String gender, String maritalStatus, String religion);
	Leave findOneById(String id);
}
