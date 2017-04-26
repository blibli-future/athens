package com.blibli.future.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blibli.future.model.EmployeeAbsencePermit;
import com.blibli.future.model.EmployeeLeave;
import com.blibli.future.repository.EmployeeLeaveRepository;
import com.blibli.future.service.api.RequestService;

@Service
public class RequestServiceImpl implements RequestService{
	private EmployeeLeaveRepository employeeLeaveRepository;
	private EmployeeAbsencePermit employeeAbsencePermit;
	
	@Autowired
	public RequestServiceImpl(EmployeeLeaveRepository employeeLeaveRepository, EmployeeAbsencePermit employeeAbsencePermit){
		this.employeeLeaveRepository = employeeLeaveRepository;
		this.employeeAbsencePermit = employeeAbsencePermit;
	}

	@Override
	public boolean sentRequest(String nik, String idLeave, LocalDate startDate, LocalDate endDate, String reason) {
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
	
	
}
