package com.blibli.future.service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Year;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blibli.future.model.EmployeeAbsencePermit;
import com.blibli.future.model.EmployeeLeave;
import com.blibli.future.repository.EmployeeLeaveRepository;
import com.blibli.future.service.api.EmployeeLeaveService;

@Service
public class EmployeeLeaveServiceImpl implements EmployeeLeaveService{
	private EmployeeLeaveRepository employeeLeaveRepository;
	
	@Autowired
	public EmployeeLeaveServiceImpl(EmployeeLeaveRepository employeeLeaveRepository){
		this.employeeLeaveRepository = employeeLeaveRepository;
	}

	@Override
	public boolean sentLeaveRequest(String nik, String idLeave, LocalDate startDate, LocalDate endDate, String reason) {
		if(nik!=null && idLeave!=null && startDate!=null && endDate!=null)
		{
			EmployeeLeave employeeLeave = new EmployeeLeave(nik, idLeave, startDate, endDate, reason);
			employeeLeaveRepository.save(employeeLeave);
			return true;
		}
		else{
			return false;
		}
	}

	@Override
	public List<EmployeeLeave> getLeaveRequest(String nik) {
		if(nik!=null)
		{
			//Get First Date Of Year
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.YEAR, Year.now().getValue());
			cal.set(Calendar.DAY_OF_YEAR, 1); 
			LocalDate dateStart = cal.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			//Get End Date Of Year
			cal.set(Calendar.YEAR, Year.now().getValue());
			cal.set(Calendar.MONTH, 11); // 11 = december
			cal.set(Calendar.DAY_OF_MONTH, 31);
			LocalDate dateEnd = cal.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			
			List<EmployeeLeave> listEmployeeLeave = new ArrayList<>();
			listEmployeeLeave = employeeLeaveRepository.findByNikAndRequestDateBetween(nik, dateStart, dateEnd);
			if(listEmployeeLeave!=null)
				return listEmployeeLeave;
			else
				System.out.println("getLeaveRequest null!");
		}
		return null;
	}

	@Override
	public boolean updateLeaveRequest(String id, String nik, String idLeave, LocalDate startDate, LocalDate endDate,
			String reason) {
		if(id!=null && nik!=null && idLeave!=null && startDate!=null && endDate!=null)
		{
			EmployeeLeave oldEmployeeLeave = employeeLeaveRepository.findById(id);
			if(oldEmployeeLeave!=null)
				employeeLeaveRepository.delete(oldEmployeeLeave);
			else
				return false;
			EmployeeLeave employeeLeave = new EmployeeLeave(nik, idLeave, startDate, endDate, reason);
			employeeLeaveRepository.save(employeeLeave);
			return true;
		}
		else{
			return false;
		}
	}
}
