
package com.blibli.future.repository;

import com.blibli.future.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, String> {
	public Attendance findOneByNikAndDate(String nik, LocalDate date);
	public List<Attendance> findByDateBetween(LocalDate dateStart,LocalDate dateEnd);
}
