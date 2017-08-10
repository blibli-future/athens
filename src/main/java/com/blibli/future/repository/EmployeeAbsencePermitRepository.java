package com.blibli.future.repository;

import java.util.List;
import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.blibli.future.enums.Status;
import com.blibli.future.model.Employee;
import com.blibli.future.model.EmployeeAbsencePermit;
import com.blibli.future.vo.PermissionResponseVo;

@Repository
public interface EmployeeAbsencePermitRepository extends JpaRepository<EmployeeAbsencePermit, String>{
	List<EmployeeAbsencePermit> findByEmployeeAndRequestDateBetween(Employee employee, LocalDate dateStart,LocalDate dateEnd);
	EmployeeAbsencePermit findOneById(String id);
	
	@Query(value = "select eeap.nik, eeap.fullname, eeap.nameOfDept, sum(eeap.sumDay) from (" +
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
	List<Object[]> countEachEmployeeAbsencePermitByDepartmentDateBetween(int absencePermit, String department, LocalDate startDate, LocalDate endDate);
	
	@Query("select new com.blibli.future.vo.PermissionResponseVo(eap.id, eap.employee.nik, e.fullName, eap.startDate, eap.endDate, eap.status, 'absence', eap.processedBy, eap.reason) "
			+ "from EmployeeAbsencePermit eap join Employee e on eap.employee.nik = e.nik where e.chiefNik = (?1) and eap.status = (?2)")
	List<PermissionResponseVo> getEmployeeAbsencePermitByChiefNikStatus(String chiefNik, Status status);
	
	@Query("select new com.blibli.future.vo.PermissionResponseVo(eap.id, eap.employee.nik, e.fullName, eap.startDate, eap.endDate, eap.status, 'absence', eap.processedBy, eap.reason) "
			+ "from EmployeeAbsencePermit eap join Employee e on eap.employee.nik = e.nik where e.nik = (?1) and eap.status = (?2)")
	List<PermissionResponseVo> getEmployeeAbsencePermitByNikStatus(String nik, Status status);
	
	@Query(value = "select manyDays.dept, manyDays.day, count(manyDays) as countedDays from "
			+ "(select e.name_of_dept as dept, generate_series(date_trunc('day', eap.start_date), eap.end_date, '1 day')//://:date "
			+ "as day from employee e join employee_absence_permit eap on e.nik = eap.employee_nik "
			+ "where eap.absence_permit = (?1) and eap.status = (?2) and e.name_of_dept = (?3) "
			+ "and start_date between ?4//://:date-20 and ?4//://:date+20 order by day) as manyDays "
			+ "where (manyDays.day between ((?4)-(?5)) and (?4)) group by manyDays.dept, manyDays.day "
			+ "order by manyDays.day asc"
			, nativeQuery = true)
	List<Object[]> countOneDepartmentAbsencePermitByAbsencePermitStatusDepartmentDate(int absencePermitOrdinal, int requestStatusOrdinal, String department, LocalDate initDate, int totalDaysBackward);
	
	@Query(value = "select eeap.nik, eeap.fullname, eeap.nameOfDept, sum(eeap.sumDay) as totalDays from (" +
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
			") AS eeap GROUP BY eeap.nik order by totalDays desc fetch first 10 rows only"
			, nativeQuery = true)
	List<Object[]> countTop10EmployeeAbsencePermitByDepartmentDateBetween(int absencePermit, String department, LocalDate startDate, LocalDate endDate);
}