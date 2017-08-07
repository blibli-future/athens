package com.blibli.future.repository;

import com.blibli.future.enums.Gender;
import com.blibli.future.enums.MaritalStatus;
import com.blibli.future.enums.Religion;
import com.blibli.future.model.Leave;
import com.blibli.future.vo.LeaveResponseVo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveRepository extends JpaRepository<Leave, String> {
	List<Leave> findByGenderAndMaritalStatusAndReligion(Gender gender, MaritalStatus maritalStatus, Religion religion);
	Leave findOneById(String id);
	@Query("select new com.blibli.future.vo.LeaveResponseVo(id, name) from Leave where gender = (?1) and maritalStatus = (?2) and religion = (?3)")
	List<LeaveResponseVo> findLeaveByGenderAndMaritalStatusAndReligion(Gender gender, MaritalStatus maritalStatus, Religion religion);
}
