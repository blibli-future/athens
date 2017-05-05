package com.blibli.future.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blibli.future.model.EmployeeAbsencePermit;
import com.blibli.future.repository.EmployeeAbsencePermitRepository;
import com.blibli.future.service.api.EmployeeAbsencePermitService;

@Service
public class EmployeeAbsencePermitServiceImpl implements EmployeeAbsencePermitService{
	private EmployeeAbsencePermitRepository employeeAbsencePermitRepository;
	
	@Autowired
	EmployeeAbsencePermitServiceImpl(EmployeeAbsencePermitRepository employeeAbsencePermitRepository){
		this.employeeAbsencePermitRepository = employeeAbsencePermitRepository;
	}
	
	@Override
	public boolean sentAbsencePermitRequest(String nik, String idAbsencePermit, LocalDate startDate, LocalDate endDate,
			String reason) {
		if(nik!=null && idAbsencePermit!=null && startDate!=null && endDate!=null){
			EmployeeAbsencePermit employeeAbsencePermit = new EmployeeAbsencePermit(nik, idAbsencePermit, startDate, endDate, reason);
			employeeAbsencePermitRepository.save(employeeAbsencePermit);
			return true;
		}
		return false;
	}

	@Override
	public List<EmployeeAbsencePermit> getAbsencePermitRequest(String nik) {
		if(nik!=null)
		{
			List<EmployeeAbsencePermit> listEmployeeAbsencePermit = new ArrayList<>();
			listEmployeeAbsencePermit = employeeAbsencePermitRepository.findByNik(nik);
			return listEmployeeAbsencePermit;
		}
		return null;
	}

}
