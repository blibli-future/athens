package com.blibli.future.repository;

import java.util.List;
import java.time.LocalDate;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.blibli.future.enums.AbsencePermit;
import com.blibli.future.model.Employee;
import com.blibli.future.model.EmployeeAbsencePermit;
import com.blibli.future.vo.SickEmployeeVo;

@Repository
public interface EmployeeAbsencePermitRepository extends JpaRepository<EmployeeAbsencePermit, String>{
	public List<EmployeeAbsencePermit> findByEmployeeAndRequestDateBetween(Employee employee, LocalDate dateStart,LocalDate dateEnd);
	public EmployeeAbsencePermit findOneById(String id);
	@Query(value = "select new com.blibli.future.vo.SickEmployeeVo(e.nik, e.fullName, e.nameOfDept, count(e)) "
			+ "from EmployeeAbsencePermit eap JOIN Employee e ON eap.employee.nik = e.nik "
			+ "WHERE e.nameOfDept = (?1) and eap.absencePermit = (?4) and (eap.requestDate between (?2) and (?3)) group by e.nik ORDER BY count(e) DESC")
	public List<SickEmployeeVo> countSickEmployee(String deparment, LocalDate startDate, LocalDate endDate, AbsencePermit absencePermit, Pageable pageable);
}
