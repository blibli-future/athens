package com.blibli.future.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blibli.future.enums.Gender;
import com.blibli.future.enums.MaritalStatus;
import com.blibli.future.enums.Religion;
import com.blibli.future.exception.IdNotFoundException;
import com.blibli.future.model.Employee;
import com.blibli.future.model.Leave;
import com.blibli.future.repository.EmployeeRepository;
import com.blibli.future.repository.LeaveRepository;
import com.blibli.future.service.api.LeaveService;

@Service
public class LeaveServiceImpl implements LeaveService{

	private LeaveRepository leaveRepository;
	private EmployeeRepository employeeRepository;
	
	@Autowired
	LeaveServiceImpl(LeaveRepository leaveRepository, EmployeeRepository employeeRepository){
		this.leaveRepository = leaveRepository;
		this.employeeRepository = employeeRepository;
	}
	
	@Override
	public List<Leave> getLeave(String nik) throws IdNotFoundException{
		Employee emp = employeeRepository.findOneByNik(nik);
		if(emp == null)
    		throw new IdNotFoundException("NIK: " + nik + " was not found");
		List<Leave> leave = leaveRepository.findByGenderAndMaritalStatusAndReligion(emp.getGender(), emp.getMaritalStatus(), emp.getReligion());
		return leave;
	}
}
