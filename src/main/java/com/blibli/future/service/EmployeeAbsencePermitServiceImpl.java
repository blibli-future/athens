package com.blibli.future.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blibli.future.model.Employee;
import com.blibli.future.model.EmployeeAbsencePermit;
import com.blibli.future.repository.EmployeeAbsencePermitRepository;
import com.blibli.future.repository.EmployeeRepository;
import com.blibli.future.service.api.EmployeeAbsencePermitService;
import com.blibli.future.vo.EmployeeAbsencePermitVo;

@Service
public class EmployeeAbsencePermitServiceImpl implements EmployeeAbsencePermitService{
	private EmployeeAbsencePermitRepository employeeAbsencePermitRepository;
	private EmployeeRepository employeeRepository;
	
	@Autowired
	EmployeeAbsencePermitServiceImpl(EmployeeAbsencePermitRepository employeeAbsencePermitRepository, EmployeeRepository employeeRepository){
		this.employeeAbsencePermitRepository = employeeAbsencePermitRepository;
		this.employeeRepository = employeeRepository;
	}
	
	@Override
	public EmployeeAbsencePermit sentAbsencePermitRequest(EmployeeAbsencePermitVo employeeAbsencePermitVo) {
		if(employeeAbsencePermitVo!=null){
			Employee emp = employeeRepository.findOneByNik(employeeAbsencePermitVo.getNik());
			if(emp!=null)
			{
				EmployeeAbsencePermit employeeAbsencePermit = EmployeeAbsencePermit.convertToEmployeeAbsencePermit(employeeAbsencePermitVo, emp);
				EmployeeAbsencePermit savedEmployeeAbsencePermit =  employeeAbsencePermitRepository.save(employeeAbsencePermit);
				if(savedEmployeeAbsencePermit!=null)
				{
					//Log EmployeeAbsencePermit Is Saved Dudeh
					return savedEmployeeAbsencePermit;
				}
				//Log fail dudeh
				return null;
			}
			//Log Employee not found Dudeh
			return null;
		}
		//Log VO Is Null Dudeh
		return null;
	}

	@Override
	public List<EmployeeAbsencePermit> getAbsencePermitRequest(String nik) {
		if(nik!=null)
		{
			Employee emp = employeeRepository.findOneByNik(nik);
			if(emp!=null)
			{
				//Get First Date Of Year
				int year = LocalDate.now().getYear();
				int month = 1; 
				int dayOfMonth = 1;
				LocalDate dateStart = LocalDate.of(year, month, dayOfMonth);
				//Get End Date Of Year
				year = LocalDate.now().getYear();
				month = 12;
				dayOfMonth = 31;
				LocalDate dateEnd = LocalDate.of(year, month, dayOfMonth);
				
				List<EmployeeAbsencePermit> listEmployeeAbsencePermit = new ArrayList<>();
				listEmployeeAbsencePermit = employeeAbsencePermitRepository.findByEmployeeAndRequestDateBetween(emp, dateStart, dateEnd);
				if(listEmployeeAbsencePermit!=null){
					//Log sukses dudeh
					return listEmployeeAbsencePermit;
				}
				//Log null dudeh
				return null;
			}
			//Log employee not found dudeh
			return null;
		}
		//Log nik null dudeh
		return null;
	}

	@Override
	public EmployeeAbsencePermit updateAbsencePermitRequest(EmployeeAbsencePermitVo employeeAbsencePermitVo) {
		if(employeeAbsencePermitVo!=null){
			EmployeeAbsencePermit oldEmployeeAbsencePermit = employeeAbsencePermitRepository.findOneById(employeeAbsencePermitVo.getId());
			if(oldEmployeeAbsencePermit != null)
			{
				oldEmployeeAbsencePermit.updateEmployeeAbsencePermit(employeeAbsencePermitVo);
				EmployeeAbsencePermit updatedEmployeeAbsencePermit = employeeAbsencePermitRepository.save(oldEmployeeAbsencePermit);
				if(updatedEmployeeAbsencePermit!=null)
				{
					//Log EmployeeAbsencePermit updated dudeh
					return updatedEmployeeAbsencePermit;
				}
				//Log update fail dudeh
				return null;
			}
			//Log EmployeeAbsencePermit not found dudeh
			return null;
		}
		//Log Vo is null dudeh
		return null;
	}

}
