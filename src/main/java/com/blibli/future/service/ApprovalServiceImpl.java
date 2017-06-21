package com.blibli.future.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;

import com.blibli.future.model.EmployeeAbsencePermit;
import com.blibli.future.model.EmployeeLeave;
import com.blibli.future.repository.EmployeeAbsencePermitRepository;
import com.blibli.future.repository.EmployeeLeaveRepository;
import com.blibli.future.service.api.ApprovalService;

public class ApprovalServiceImpl implements ApprovalService{

	private EmployeeAbsencePermitRepository employeeAbsencePermitRepository;
	private EmployeeLeaveRepository employeeLeaveRepository;
	
	@Autowired
	ApprovalServiceImpl(EmployeeAbsencePermitRepository employeeAbsencePermitRepository, EmployeeLeaveRepository employeeLeaveRepository){
		this.employeeAbsencePermitRepository = employeeAbsencePermitRepository;
		this.employeeLeaveRepository = employeeLeaveRepository;
	}

	@Override
	public EmployeeAbsencePermit absencePermitApproval(String idAbsencePermit, String nik) {
		EmployeeAbsencePermit employeeAbsencePermit = employeeAbsencePermitRepository.findOneById(idAbsencePermit);
		if(employeeAbsencePermit!=null)
		{
			employeeAbsencePermit.setApprovedBy(nik);
			employeeAbsencePermit.setApprovedDate(LocalDate.now());
			//TODO log sukses dudeh
			return employeeAbsencePermitRepository.save(employeeAbsencePermit);
		}
		//TODO log null dudeh
		return null;
	}

	@Override
	public EmployeeLeave leaveApproval(String idLeave, String nik) {
		EmployeeLeave employeeLeave = employeeLeaveRepository.findOneById(idLeave);
		if(employeeLeave!=null)
		{
			employeeLeave.setApprovedBy(nik);
			employeeLeave.setApprovedDate(LocalDate.now());
			//TODO Log sukses dudeh
			return employeeLeaveRepository.save(employeeLeave);
		}
		//TODO Log null dudeh
		return null;
	}
	
}
