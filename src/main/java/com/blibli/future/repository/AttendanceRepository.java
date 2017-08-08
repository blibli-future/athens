package com.blibli.future.repository;

import com.blibli.future.model.Attendance;
import com.blibli.future.model.primaryKey.AttendanceKey;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, AttendanceKey> {
	Attendance findByAttendanceKey(AttendanceKey attendanceKey);
	List<Attendance> findByAttendanceKeyDateBetween(LocalDate dateStart, LocalDate dateEnd);

	@Query(value = "select e.nik, e.full_Name, e.name_Of_Dept, count(atte) " +
			"from attendance atte JOIN Employee e ON atte.nik = e.nik " +
			"WHERE e.name_Of_Dept = (?1) and (atte.date between (?2) and (?3)) " +
			"group by e.nik"
			, nativeQuery = true)
	List<Object[]> countEachEmployeeAttendanceByDepartmentDateBetween(String department, LocalDate startDate, LocalDate endDate);

	@Query(value = "select e.nik, e.full_Name, e.name_Of_Dept, count(atte)" +
			"from attendance atte JOIN Employee e ON atte.nik = e.nik " +
			"WHERE atte.tap_out is null and e.name_Of_Dept = (?1) and (atte.date between (?2) and (?3)) " +
			"group by e.nik"
			, nativeQuery = true)
	List<Object[]> countEachEmployeeNoTapOutDateByDepartmentDateBetween(String department, LocalDate startDate, LocalDate endDate);

	@Query(value = "select e.nik, e.full_Name, e.name_Of_Dept, count(atte)" +
			"from attendance atte JOIN Employee e ON atte.nik = e.nik " +
			"WHERE atte.early_leave_hour > 0 and e.name_Of_Dept = (?1) and (atte.date between (?2) and (?3)) " +
			"group by e.nik"
			, nativeQuery = true)
	List<Object[]> countEachEmployeeEarlyLeaveByDepartmentDateBetween(String department, LocalDate startDate, LocalDate endDate);

	@Query(value = "select e.nik, e.full_Name, e.name_Of_Dept, count(atte)" +
			"from attendance atte JOIN Employee e ON atte.nik = e.nik " +
			"WHERE atte.late_condition = (?1) and e.name_Of_Dept = (?2) and (atte.date between (?3) and (?4)) " +
			"group by e.nik"
			, nativeQuery = true)
	List<Object[]> countEachEmployeeLateConditionByDepartmentDateBetween(int lateConditionOrdinal, String department, LocalDate startDate, LocalDate endDate);

	@Query(value = "select e.nik, e.full_Name, e.name_Of_Dept, count(atte)" +
			"from attendance atte JOIN Employee e ON atte.nik = e.nik " +
			"WHERE atte.late_condition = (?1) and e.nik = (?2) and (atte.date between (?3) and (?4)) " +
			"group by e.nik"
			, nativeQuery = true)
	Object[] countEmployeeLateConditionByNikDateBetween(int lateConditionOrdinal, String nik, LocalDate startDate, LocalDate endDate);

	@Query(value = "select e.nik, e.full_Name, e.name_Of_Dept, " +
			" ((select count(*) from generate_series((?2)\\:\\:date, (?3)\\:\\:date, '1 day') as cale "
			+ "WHERE  extract('ISODOW' FROM cale) < 6) - count(atte)) as notAttend "
			+ "from attendance atte JOIN Employee e ON atte.nik = e.nik "
			+ "WHERE e.name_Of_Dept = (?1) and (atte.date between (?2) and (?3)) "
			+ "and extract('ISODOW' FROM atte.date) < 6 group by e.nik", nativeQuery = true)
	List<Object[]> countEachEmployeeAbsenceByDepartmentDateBetween(String department, LocalDate startdate, LocalDate endDate);
	
	@Query(value = "select e.name_Of_Dept, atte.date, count(atte)" +
			"from attendance atte JOIN Employee e ON atte.nik = e.nik " +
			"WHERE atte.late_condition = (?1) and e.name_Of_Dept = (?2) and (atte.date between (?3) and (?4)) " +
			"group by e.name_Of_Dept"
			, nativeQuery = true)
	List<Object[]> countEachDepartmentLateConditionByDepartmentDateBetween(int lateConditionOrdinal, String department, LocalDate startDate, LocalDate endDate);
	
	@Query(value = "select e.nik, e.full_Name, e.name_Of_Dept, count(atte) as counted " +
			"from attendance atte JOIN Employee e ON atte.nik = e.nik " +
			"WHERE atte.late_condition = (?1) and e.name_Of_Dept = (?2) and (atte.date between (?3) and (?4)) " +
			"group by e.nik order by counted desc fetch first 10 rows only"
			, nativeQuery = true)
	List<Object[]> countTop10EmployeeLateConditionByDepartmentDateBetween(int lateConditionOrdinal, String department, LocalDate startDate, LocalDate endDate);
}