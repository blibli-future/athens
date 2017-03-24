
package com.blibli.future.repository;

import com.blibli.future.model.Attendance;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, String> {
	public Attendance findOneByNikAndDate(String nik, Date date);

}
