package com.blibli.future.repository;

import com.blibli.future.model.Employee;
import com.blibli.future.model.EmployeeLeave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeLeaveRepository extends JpaRepository<EmployeeLeave, Long>{
	List<EmployeeLeave> findByEmployeeAndRequestDateBetween(Employee employee, LocalDate dateStart, LocalDate dateEnd);
	EmployeeLeave findOneById(String id);
}
