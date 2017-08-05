package com.blibli.future.repository;

import com.blibli.future.enums.Gender;
import com.blibli.future.enums.MaritalStatus;
import com.blibli.future.enums.Religion;
import com.blibli.future.model.Leave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveRepository extends JpaRepository<Leave, String> {
	List<Leave> findByGenderAndMaritalStatusAndReligion(Gender gender, MaritalStatus maritalStatus, Religion religion);
	Leave findOneById(String id);
}
