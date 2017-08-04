package com.blibli.future.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.blibli.future.model.EmployeeYearlyLeave;

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
}
