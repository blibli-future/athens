package com.blibli.future.repository;

import com.blibli.future.enums.Status;
import com.blibli.future.model.Employee;
import com.blibli.future.model.EmployeeLeave;
import com.blibli.future.vo.PermissionResponseVo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeLeaveRepository extends JpaRepository<EmployeeLeave, Long>{
	List<EmployeeLeave> findByEmployeeAndRequestDateBetween(Employee employee, LocalDate dateStart, LocalDate dateEnd);
	EmployeeLeave findOneById(String id);
	
	@Query("select new com.blibli.future.vo.PermissionResponseVo(el.id, el.employee.nik, e.fullName, el.startDate, el.endDate, el.status, 'leave', el.processedBy, el.reason) "
			+ "from EmployeeLeave el join Employee e on el.employee.nik = e.nik where e.chiefNik = (?1) and el.status = (?2)")
	List<PermissionResponseVo> getEmployeeLeaveByChiefNikStatus(String chiefNik, Status status);
	
	@Query("select new com.blibli.future.vo.PermissionResponseVo(el.id, el.employee.nik, e.fullName, el.startDate, el.endDate, el.status, 'leave', el.processedBy, el.reason) "
			+ "from EmployeeLeave el join Employee e on el.employee.nik = e.nik where e.nik = (?1) and el.status = (?2)")
	List<PermissionResponseVo> getEmployeeLeaveByNikStatus(String nik, Status status);
	
	@Query(value = "select manyDays.dept, manyDays.day, count(manyDays) as countedDays from "
			+ "(select e.name_of_dept as dept, generate_series(date_trunc('day', el.start_date), el.end_date, '1 day')::date "
			+ "as day from employee e join employee_leave el on e.nik = el.employee_nik "
			+ "where el.status = (?1) and e.name_of_dept = (?2) "
			+ "and start_date between (?3)//://:date-20 and (?3)//://:date+20 order by day) as manyDays "
			+ "where (manyDays.day between ((?3)-(?4)) and (?3)) group by manyDays.dept, manyDays.day "
			+ "order by manyDays.day asc"
			, nativeQuery = true)
	List<Object[]> countOneDepartmentSpecialLeaveByStatusDepartmentDate(int requestStatusOrdinal, String department, LocalDate initDate, int totalDaysBackward);
	
	@Query(value = "select eel.nik, eel.fullname, eel.nameOfDept, sum(eel.sumDay) as totalDays from (" +
			"  select e.nik as nik, e.full_Name as fullname, e.name_Of_Dept as nameOfDept" +
			"  , case " +
			"      when (start_Date<=(?3)) and (end_Date>=(?4)) then (?4\\:\\:date - ?3\\:\\:date)+1 " +
			"      when start_Date<=(?3) then (end_Date - (?3))+1 " +
			"      when end_Date>=(?4) then ((?4)-start_Date)+1 " +
			"      else (end_Date-start_Date)+1 " +
			"    end as sumDay " +
			"  from employee_leave el join employee e on el.employee_nik = e.nik " +
			"  where el.status = (?1) AND e.name_Of_Dept = (?2) AND (" +
			"   ((?3) BETWEEN start_Date and end_Date) OR ((?4) BETWEEN start_Date AND end_Date))" +
			") AS eel GROUP BY eel.nik order by totalDays desc fetch first 10 rows only"
			, nativeQuery = true)
	List<Object[]> countTop10EmployeeSpecialLeaveByStatusDepartmentDateBetween(int requestStatusOrdinal, String department, LocalDate startDate, LocalDate endDate);
}