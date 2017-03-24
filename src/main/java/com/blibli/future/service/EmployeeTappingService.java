package com.blibli.future.service;

import java.sql.Timestamp;
import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.blibli.future.model.Attendance;
import com.blibli.future.repository.AttendanceRepository;

@Service
public class EmployeeTappingService {
	private AttendanceRepository attendanceRepo;
	
	@Autowired
	public EmployeeTappingService(AttendanceRepository attendanceRepo){
		this.attendanceRepo = attendanceRepo;
	}
	
	public boolean processTapping(String type, String nik, Timestamp tapTime, Date dateTap){
		if(type!=null || tapTime!=null || dateTap!=null || nik!=null){
			if(type.equalsIgnoreCase("in")){
				Attendance attendance = new Attendance(nik, tapTime, null, dateTap);
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
