package com.blibli.future.service;

import com.blibli.future.model.Attendance;
import com.blibli.future.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class EmployeeTappingService {
	private AttendanceRepository attendanceRepo;
	
	@Autowired
	public EmployeeTappingService(AttendanceRepository attendanceRepo){
		this.attendanceRepo = attendanceRepo;
	}
	
	public boolean processTapping(String type, String nik, LocalDate dateTap, LocalTime tapTime){
		if(type!=null && tapTime!=null && dateTap!=null && nik!=null){
			if(type.equalsIgnoreCase("in")){
				Attendance attendance = new Attendance(nik, dateTap, tapTime, null);
				attendanceRepo.save(attendance);
			}
			else{
				Attendance attendance = attendanceRepo.findOneByNikAndDate(nik, dateTap);
				attendance.setTapOut(tapTime);
				attendanceRepo.save(attendance);
			}
			return true;
		}
		else
			return false;
	}
}
