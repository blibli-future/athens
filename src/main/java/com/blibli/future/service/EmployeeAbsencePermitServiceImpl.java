package com.blibli.future.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blibli.future.model.EmployeeAbsencePermit;
import com.blibli.future.service.api.EmployeeAbsencePermitService;

@Service
public class EmployeeAbsencePermitServiceImpl implements EmployeeAbsencePermitService{
//	private EmployeeAbsencePermitRepository employeeAbsencePermitRepository;
//	
//	@Autowired
//	EmployeeAbsencePermitServiceImpl(EmployeeAbsencePermitRepository employeeAbsencePermitRepository){
//		this.employeeAbsencePermitRepository = employeeAbsencePermitRepository;
//	}
//	
//	@Override
//	public boolean sentAbsencePermitRequest(String nik, String idAbsencePermit, LocalDate startDate, LocalDate endDate,
//			String reason) {
//		if(nik!=null && idAbsencePermit!=null && startDate!=null && endDate!=null){
//			EmployeeAbsencePermit employeeAbsencePermit = new EmployeeAbsencePermit(nik, idAbsencePermit, startDate, endDate, reason);
//			employeeAbsencePermitRepository.save(employeeAbsencePermit);
//			return true;
//		}
//		return false;
//	}
//
//	@Override
//	public List<EmployeeAbsencePermit> getAbsencePermitRequest(String nik) {
//		if(nik!=null)
//		{
//			//Get First Date Of Year
//			int year = LocalDate.now().getYear();
//			int month = 1; 
//			int dayOfMonth = 1;
//			LocalDate dateStart = LocalDate.of(year, month, dayOfMonth);
//			//Get End Date Of Year
//			year = LocalDate.now().getYear();
//			month = 12;
//			dayOfMonth = 31;
//			LocalDate dateEnd = LocalDate.of(year, month, dayOfMonth);
//			
//			List<EmployeeAbsencePermit> listEmployeeAbsencePermit = new ArrayList<>();
//			listEmployeeAbsencePermit = employeeAbsencePermitRepository.findByNikAndRequestDateBetween(nik, dateStart, dateEnd);
//			return listEmployeeAbsencePermit;
//		}
//		return null;
//	}
//
//	@Override
//	public boolean updateAbsencePermitRequest(String id, String nik, String idAbsencePermit, LocalDate startDate,
//			LocalDate endDate, String reason) {
//		if(id!=null && nik!=null && idAbsencePermit!=null && startDate!=null && endDate!=null){
//			EmployeeAbsencePermit oldEmployeeAbsencePermit = employeeAbsencePermitRepository.findById(id);
//			if(oldEmployeeAbsencePermit != null)
//				employeeAbsencePermitRepository.delete(oldEmployeeAbsencePermit);
//			else
//				return false;
//			EmployeeAbsencePermit employeeAbsencePermit = new EmployeeAbsencePermit(nik, idAbsencePermit, startDate, endDate, reason);
//			employeeAbsencePermitRepository.save(employeeAbsencePermit);
//			return true;
//		}
//		return false;
//	}

}
