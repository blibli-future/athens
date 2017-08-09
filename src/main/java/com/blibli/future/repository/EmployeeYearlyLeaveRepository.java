package com.blibli.future.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.blibli.future.enums.Status;
import com.blibli.future.model.EmployeeYearlyLeave;
import com.blibli.future.vo.PermissionResponseVo;

@Repository
public interface EmployeeYearlyLeaveRepository extends JpaRepository<EmployeeYearlyLeave, String>{
	@Query(value = "select eyl.nik, eyl.fullname, eyl.nameOfDept, sum(eyl.sumDay)" +
            "from (" +
            "  select e.nik as nik, e.full_Name as fullname, e.name_Of_Dept as nameOfDept, " +
            "    case when (start_Date<=(?2)) and (end_Date>=(?3)) then (?3\\:\\:date - ?2\\:\\:date)+1 " +
            "      when start_Date<=(?2) then (end_Date - (?2))+1 " +
            "      when end_Date>=(?3) then ((?3)-start_Date)+1 " +
            "      else (end_Date-start_Date)+1 " +
            "    end as sumDay " +
            "  from employee_yearly_leave yl join employee e on yl.employee_nik = e.nik " +
            "  where e.name_Of_Dept = (?1) AND (" +
            "    ((?2) BETWEEN start_Date and end_Date) OR ((?3) BETWEEN start_Date AND end_Date))" +
            ") AS eyl GROUP BY eyl.nik, eyl.fullname, eyl.nameOfDept"
            , nativeQuery = true)
	List<Object[]> sumEachEmployeeYearlyLeaveByDepartmentDateBetween(String department, LocalDate startdate, LocalDate endDate);

	@Query(value = "select eyl.nik, eyl.fullname, eyl.nameOfDept, sum(eyl.sumDay)" +
			"from (" +
			"  select e.nik as nik, e.full_Name as fullname, e.name_Of_Dept as nameOfDept, " +
			"    case when (start_Date<=(?2)) and (end_Date>=(?3)) then (?3\\:\\:date - ?2\\:\\:date)+1 " +
			"      when start_Date<=(?2) then (end_Date - (?2))+1 " +
			"      when end_Date>=(?3) then ((?3)-start_Date)+1 " +
			"      else (end_Date-start_Date)+1 " +
			"    end as sumDay " +
			"  from employee_yearly_leave yl join employee e on yl.employee_nik = e.nik " +
			"  where e.nik = (?1) AND (" +
			"    ((?2) BETWEEN start_Date and end_Date) OR ((?3) BETWEEN start_Date AND end_Date))" +
			") AS eyl GROUP BY eyl.nik, eyl.fullname, eyl.nameOfDept"
			, nativeQuery = true)
	Object[] sumEmployeeYearlyLeaveByNikDateBetween(String nik, LocalDate startdate, LocalDate endDate);
	
	@Query("select new com.blibli.future.vo.PermissionResponseVo(el.id, el.employee.nik, e.fullName, el.startDate, el.endDate, el.status, 'yearly', el.processedBy, el.reason) "
			+ "from EmployeeYearlyLeave el join Employee e on el.employee.nik = e.nik where e.chiefNik = (?1) and el.status = (?2)")
	List<PermissionResponseVo> getEmployeeYearlyLeaveByChiefNikStatus(String chiefNik, Status status);
	
	@Query("select new com.blibli.future.vo.PermissionResponseVo(el.id, el.employee.nik, e.fullName, el.startDate, el.endDate, el.status, 'yearly', el.processedBy, el.reason) "
			+ "from EmployeeYearlyLeave el join Employee e on el.employee.nik = e.nik where e.nik = (?1) and el.status = (?2)")
	List<PermissionResponseVo> getEmployeeYearlyLeaveByNikStatus(String nik, Status status);
	
	@Query(value = "select manyDays.dept, manyDays.day, count(manyDays) as countedDays from "
			+ "(select e.name_of_dept as dept, generate_series(date_trunc('day', eyl.start_date), eyl.end_date, '1 day')::date "
			+ "as day from employee e join employee_substituion_leave eyl on e.nik = eyl.employee_nik "
			+ "where eyl.status = (?1) and e.name_of_dept = (?2) "
			+ "and start_date between (?3)//://:date-20 and (?3)//://:date+20 order by day) as manyDays "
			+ "where (manyDays.day between ((?3)-(?4)) and (?3)) group by manyDays.dept, manyDays.day "
			+ "order by manyDays.day asc"
			, nativeQuery = true)
	List<Object[]> countOneDepartmentYearlyLeaveByStatusDepartmentDate(int requestStatusOrdinal, String department, LocalDate initDate, int totalDaysBackward);
	
	@Query(value = "select eeyl.nik, eeyl.fullname, eeyl.nameOfDept, sum(eeyl.sumDay) as totalDays from (" +
			"  select e.nik as nik, e.full_Name as fullname, e.name_Of_Dept as nameOfDept" +
			"  , case " +
			"      when (start_Date<=(?3)) and (end_Date>=(?4)) then (?4\\:\\:date - ?3\\:\\:date)+1 " +
			"      when start_Date<=(?3) then (end_Date - (?3))+1 " +
			"      when end_Date>=(?4) then ((?4)-start_Date)+1 " +
			"      else (end_Date-start_Date)+1 " +
			"    end as sumDay " +
			"  from employee_leave eyl join employee e on eyl.employee_nik = e.nik " +
			"  where eyl.status = (?1) AND e.name_Of_Dept = (?2) AND (" +
			"   ((?3) BETWEEN start_Date and end_Date) OR ((?4) BETWEEN start_Date AND end_Date))" +
			") AS eeyl GROUP BY eeyl.nik order by totalDays desc fetch first 10 rows only"
			, nativeQuery = true)
	List<Object[]> countTop10EmployeeSubstitutionLeaveByStatusDepartmentDateBetween(int requestStatusOrdinal, String department, LocalDate startDate, LocalDate endDate);
}
