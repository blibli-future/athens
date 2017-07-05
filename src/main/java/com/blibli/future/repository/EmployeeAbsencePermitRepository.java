package com.blibli.future.repository;

import java.util.List;
import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.blibli.future.model.Employee;
import com.blibli.future.model.EmployeeAbsencePermit;
import com.blibli.future.vo.SingleReportVo;

@Repository
public interface EmployeeAbsencePermitRepository extends JpaRepository<EmployeeAbsencePermit, String>{
	List<EmployeeAbsencePermit> findByEmployeeAndRequestDateBetween(Employee employee, LocalDate dateStart,LocalDate dateEnd);
	EmployeeAbsencePermit findOneById(String id);
	
	@Query(value = "select new com.blibli.future.vo.SingleReportVo(eeap.nik, eeap.fullname, eeap.nameOfDept, sum(eeap.sumDay)) from (" +
			"  select e.nik as nik, e.full_Name as fullname, e.name_Of_Dept as nameOfDept" +
			"  , case " +
			"      when (start_Date<=(?3)) and (end_Date>=(?4)) then (?4\\:\\:date - ?3\\:\\:date)+1 " +
			"      when start_Date<=(?3) then (end_Date - (?3))+1 " +
			"      when end_Date>=(?4) then ((?4)-start_Date)+1 " +
			"      else (end_Date-start_Date)+1 " +
			"    end as sumDay " +
			"  from employee_Absence_Permit eap join employee e on eap.employee_nik = e.nik " +
			"  where eap.absence_Permit = (?1) AND e.name_Of_Dept = (?2) AND (" +
			"   ((?3) BETWEEN start_Date and end_Date) OR ((?4) BETWEEN start_Date AND end_Date))" +
			") AS eeap GROUP BY eeap.nik, eeap.fullname, eeap.nameOfDept"
			, nativeQuery = true)
	List<SingleReportVo> countEachEmployeeAbsencePermitByDepartmentDateBetween(int absencePermit, String department, LocalDate startDate, LocalDate endDate);
}
