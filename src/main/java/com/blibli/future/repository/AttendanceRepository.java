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
	public Attendance findByAttendanceKey(AttendanceKey attendanceKey);
	public List<Attendance> findByAttendanceKeyDateBetween(LocalDate dateStart, LocalDate dateEnd);
	@Query(value = "select e.nik, e.full_Name, e.name_Of_Dept, count(atte) from attendance atte JOIN Employee e ON atte.nik = e.nik WHERE e.name_Of_Dept = (?1) and (atte.date between (?2) and (?3)) group by e.nik", nativeQuery = true)
	public List<Object[]> countEmployeeAttendance(String department, LocalDate startDate, LocalDate endDate);
	@Query(value = "select e.nik, e.full_Name, e.name_Of_Dept, count(atte) from attendance atte JOIN Employee e ON atte.nik = e.nik WHERE atte.tap_out is null and e.name_Of_Dept = (?1) and (atte.date between (?2) and (?3)) group by e.nik", nativeQuery = true)
	public List<Object[]> countEmployeeNoTapOut(String department, LocalDate startDate, LocalDate endDate);
	@Query(value = "select e.nik, e.full_Name, e.name_Of_Dept, count(atte) from attendance atte JOIN Employee e ON atte.nik = e.nik WHERE atte.early_leave_hour > 0 and e.name_Of_Dept = (?1) and (atte.date between (?2) and (?3)) group by e.nik", nativeQuery = true)
	public List<Object[]> countEmployeeEarlyLeaveHour(String department, LocalDate startDate, LocalDate endDate);
	@Query(value = "select e.nik, e.full_Name, e.name_Of_Dept, count(atte) from attendance atte JOIN Employee e ON atte.nik = e.nik WHERE atte.late_condition = (?4) and e.name_Of_Dept = (?1) and (atte.date between (?2) and (?3)) group by e.nik", nativeQuery = true)
	public List<Object[]> countEmployeeLateCondition(String department, LocalDate startDate, LocalDate endDate, int late);
	@Query(value = "select e.nik, e.full_Name, e.name_Of_Dept, ((select count(*) from generate_series((?2)\\:\\:date, (?3)\\:\\:date, '1 day') "
			+ "as cale "
			+ "WHERE  extract('ISODOW' FROM cale) < 6) - count(atte)) as notAttend "
			+ "from attendance atte JOIN Employee e ON atte.nik = e.nik "
			+ "WHERE e.name_Of_Dept = (?1) and (atte.date between (?2) and (?3)) "
			+ "and extract('ISODOW' FROM atte.date) < 6 group by e.nik", nativeQuery = true)
	public List<Object[]> countNotAttendance(String department, LocalDate startdate, LocalDate endDate);
}