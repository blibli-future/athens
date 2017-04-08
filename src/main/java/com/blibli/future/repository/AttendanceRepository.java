
package com.blibli.future.repository;

import com.blibli.future.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, String> {
	public Attendance findOneByNikAndDate(String nik, LocalDate date);

}
