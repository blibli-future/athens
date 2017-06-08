package com.blibli.future.repository;

import java.util.List;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blibli.future.model.Employee;
import com.blibli.future.model.EmployeeAbsencePermit;

@Repository
public interface EmployeeAbsencePermitRepository extends JpaRepository<EmployeeAbsencePermit, String>{
	public List<EmployeeAbsencePermit> findByEmployeeAndRequestDateBetween(Employee employee, LocalDate dateStart,LocalDate dateEnd);
	public EmployeeAbsencePermit findById(String id);
}
