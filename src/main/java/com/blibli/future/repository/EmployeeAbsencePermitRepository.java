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
import com.blibli.future.vo.SingleReportVo;

@Repository
public interface EmployeeAbsencePermitRepository extends JpaRepository<EmployeeAbsencePermit, String>{
	public List<EmployeeAbsencePermit> findByEmployeeAndRequestDateBetween(Employee employee, LocalDate dateStart,LocalDate dateEnd);
	public EmployeeAbsencePermit findOneById(String id);
	@Query(value = "select new com.blibli.future.vo.SingleReportVo(e.nik, e.fullName, e.nameOfDept, count(e)) "
			+ "from EmployeeAbsencePermit eap JOIN Employee e ON eap.employee.nik = e.nik "
			+ "WHERE e.nameOfDept = (?1) and eap.absencePermit = (?4) and (eap.requestDate between (?2) and (?3)) group by e.nik ORDER BY count(e) DESC")
	public List<SingleReportVo> countSickEmployee(String deparment, LocalDate startDate, LocalDate endDate, AbsencePermit absencePermit, Pageable pageable);
	
	@Query(value = "select eeap.nik, eeap.fullname, eeap.nameOfDept, sum(eeap.sumDay) "
			+ "from "
			+ "(select e.nik as nik, e.full_Name as fullname, e.name_Of_Dept as nameOfDept, case "
			+ "when (start_Date<=(?2)) and (end_Date>=(?3)) then (?3\\:\\:date - ?2\\:\\:date)+1 "
			+ "when start_Date<=(?2) then (end_Date - (?2))+1 "
			+ "when end_Date>=(?3) then ((?3)-start_Date)+1 "
			+ "else (end_Date-start_Date)+1 "
			+ "end as sumDay "
			+ "from employee_Absence_Permit eap join employee e on eap.employee_nik = e.nik  "
			+ "where eap.absence_Permit = (?4) AND e.name_Of_Dept = (?1) AND  "
			+ "(((?2) BETWEEN start_Date and end_Date) OR ((?3) BETWEEN start_Date AND end_Date))) AS eeap "
			+ "GROUP BY eeap.nik, eeap.fullname, eeap.nameOfDept", nativeQuery = true)
	public List<Object[]> countAbsencePermitEmployee(String deparment, LocalDate startDate, LocalDate endDate, int absencePermit);
}
