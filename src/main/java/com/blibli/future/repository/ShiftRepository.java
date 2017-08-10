package com.blibli.future.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.blibli.future.model.Shift;
import com.blibli.future.vo.ShiftVo;

@Repository
public interface ShiftRepository extends JpaRepository<Shift, String> {
	Shift findOneById(String id);
	@Query("select new com.blibli.future.vo.ShiftVo(id, name, startHour, endHour, workDay, departmentEmployee, location, false) from Shift where departmentEmployee=(?1)")
	List<ShiftVo> findAllByDept(String dept);
}
