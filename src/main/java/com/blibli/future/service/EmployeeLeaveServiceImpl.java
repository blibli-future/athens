package com.blibli.future.service;

import java.time.LocalDate;
import java.util.ArrayList;
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
			List<EmployeeLeave> listEmployeeLeave = new ArrayList<>();
			listEmployeeLeave = employeeLeaveRepository.findByNik(nik);
			if(listEmployeeLeave!=null)
				return listEmployeeLeave;
			else
				System.out.println("getLeaveRequest null!");
		}
		return null;
	}
}
