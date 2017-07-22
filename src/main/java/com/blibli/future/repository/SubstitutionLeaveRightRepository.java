package com.blibli.future.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.blibli.future.model.SubstitutionLeaveRight;

@Repository
public interface SubstitutionLeaveRightRepository extends JpaRepository<SubstitutionLeaveRight,String>{
	@Query(value = "select count(*) from substitution_leave_right where expired_date <= CURRENT_DATE and nik = (?1)"
			, nativeQuery = true)
	Object[] countSubstitutionLeaveRightAvaiableByNik(String nik);
	
}
