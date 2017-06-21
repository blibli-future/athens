package com.blibli.future.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blibli.future.model.Employee;
import com.blibli.future.model.EmployeeLeave;

@Repository
public interface EmployeeLeaveRepository extends JpaRepository<EmployeeLeave, Long>{
	public List<EmployeeLeave> findByEmployeeAndRequestDateBetween(Employee employee, LocalDate dateStart, LocalDate dateEnd);
	public EmployeeLeave findOneById(String id);
}
