package com.blibli.future.service;

import java.time.LocalDate;
import java.time.Year;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
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
			
			List<EmployeeAbsencePermit> listEmployeeAbsencePermit = new ArrayList<>();
			listEmployeeAbsencePermit = employeeAbsencePermitRepository.findByNikAndRequestDateBetween(nik, dateStart, dateEnd);
			return listEmployeeAbsencePermit;
		}
		return null;
	}

	@Override
	public boolean updateAbsencePermitRequest(String id, String nik, String idAbsencePermit, LocalDate startDate,
			LocalDate endDate, String reason) {
		if(id!=null && nik!=null && idAbsencePermit!=null && startDate!=null && endDate!=null){
			EmployeeAbsencePermit oldEmployeeAbsencePermit = employeeAbsencePermitRepository.findById(id);
			if(oldEmployeeAbsencePermit != null)
				employeeAbsencePermitRepository.delete(oldEmployeeAbsencePermit);
			else
				return false;
			EmployeeAbsencePermit employeeAbsencePermit = new EmployeeAbsencePermit(nik, idAbsencePermit, startDate, endDate, reason);
			employeeAbsencePermitRepository.save(employeeAbsencePermit);
			return true;
		}
		return false;
	}

}
