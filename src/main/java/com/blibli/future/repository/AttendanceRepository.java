package com.blibli.future.repository;

import com.blibli.future.model.Attendance;
import com.blibli.future.model.primaryKey.AttendanceKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, AttendanceKey> {
	public Attendance findByAttendanceKey(AttendanceKey attendanceKey);
	public List<Attendance> findByAttendanceKeyDateBetween(LocalDate dateStart, LocalDate dateEnd);
}