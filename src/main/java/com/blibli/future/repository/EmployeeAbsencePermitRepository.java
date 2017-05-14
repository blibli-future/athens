package com.blibli.future.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blibli.future.model.EmployeeAbsencePermit;

@Repository
public interface EmployeeAbsencePermitRepository extends JpaRepository<EmployeeAbsencePermit, String> {
	public List<EmployeeAbsencePermit> findByNikAndRequestDateBetween(String nik, LocalDate dateStart,LocalDate dateEnd);
	public EmployeeAbsencePermit findById(String id);
}
