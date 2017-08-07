package com.blibli.future.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.blibli.future.enums.Status;
import com.blibli.future.model.EmployeeSubstitutionLeaveRight;
import com.blibli.future.vo.PermissionResponseVo;

@Repository
public interface EmployeeSubstitutionLeaveRightRepository extends JpaRepository<EmployeeSubstitutionLeaveRight,String>{
	@Query(value = "select esl.nik, esl.fullname, esl.nameOfDept, sum(esl.sumDay) " +
			"from (" +
			"  select e.nik as nik, e.full_Name as fullname, e.name_Of_Dept as nameOfDept, " +
			"  case " +
			"    when (start_Date<=(?2)) and (end_Date>=(?3)) then (?3\\:\\:date - ?2\\:\\:date)+1 " +
			"    when start_Date<=(?2) then (end_Date - (?2))+1 " +
			"    when end_Date>=(?3) then ((?3)-start_Date)+1 " +
			"    else (end_Date-start_Date)+1 " +
			"  end as sumDay " +
			"  from employee_substitution_leave_right es join employee e on es.employee_nik = e.nik " +
			"  where e.name_Of_Dept = (?1) AND (" +
			"    ((?2) BETWEEN start_Date and end_Date) OR ((?3) BETWEEN start_Date AND end_Date))" +
			") AS esl GROUP BY esl.nik, esl.fullname, esl.nameOfDept"
			, nativeQuery = true)
	List<Object[]> sumEachEmployeeSubstitutionLeaveRightByDepartmentDateBetween(String department, LocalDate startdate, LocalDate endDate);

	@Query(value = "select esl.nik, esl.fullname, esl.nameOfDept, sum(esl.sumDay) " +
			"from (" +
			"  select e.nik as nik, e.full_Name as fullname, e.name_Of_Dept as nameOfDept, " +
			"  case " +
			"    when (start_Date<=(?2)) and (end_Date>=(?3)) then (?3\\:\\:date - ?2\\:\\:date)+1 " +
			"    when start_Date<=(?2) then (end_Date - (?2))+1 " +
			"    when end_Date>=(?3) then ((?3)-start_Date)+1 " +
			"    else (end_Date-start_Date)+1 " +
			"  end as sumDay " +
			"  from employee_substitution_leave_right es join employee e on es.employee_nik = e.nik " +
			"  where e.nik = (?1) AND (" +
			"    ((?2) BETWEEN start_Date and end_Date) OR ((?3) BETWEEN start_Date AND end_Date))" +
			") AS esl GROUP BY esl.nik, esl.fullname, esl.nameOfDept"
			, nativeQuery = true)
	Object[] sumEmployeeSubstitutionLeaveRightByNikDateBetween(String nik, LocalDate startdate, LocalDate endDate);
	
	@Query("select new com.blibli.future.vo.PermissionResponseVo(el.id, el.employee.nik, e.fullName, el.startDate, el.endDate, el.status, 'subtitution', el.processedBy, el.reason) "
			+ "from EmployeeSubstitutionLeaveRight el join Employee e on el.employee.nik = e.nik where e.chiefNik = (?1) and el.status = (?2)")
	List<PermissionResponseVo> getEmployeeSubstitutionLeaveRightByChiefNikStatus(String chiefNik, Status status);
	
	@Query("select new com.blibli.future.vo.PermissionResponseVo(esl.id, esl.employee.nik, e.fullName, esl.startDate, esl.endDate, esl.status, 'subtitution', esl.processedBy, esl.reason) "
			+ "from EmployeeSubstitutionLeaveRight esl join Employee e on esl.employee.nik = e.nik where e.nik = (?1) and esl.status = (?2)")
	List<PermissionResponseVo> getEmployeeSubstitutionLeaveRightByNikStatus(String nik, Status status);
}