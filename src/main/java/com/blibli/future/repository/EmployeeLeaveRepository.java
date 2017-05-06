package com.blibli.future.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blibli.future.model.EmployeeLeave;

@Repository
public interface EmployeeLeaveRepository extends JpaRepository<EmployeeLeave, String> {
	public List<EmployeeLeave> findByNikAndRequestDateBetween(String nik, LocalDate dateStart,LocalDate dateEnd);
	public EmployeeLeave findById(String id);
}
