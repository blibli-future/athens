package com.blibli.future.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blibli.future.model.EmployeeShift;
import com.blibli.future.repository.EmployeeShiftRepository;

@Service
public class EmployeeShiftingService {
	private EmployeeShiftRepository shiftRepo;
	
	@Autowired
	public EmployeeShiftingService(EmployeeShiftRepository shiftRepo){
		this.shiftRepo = shiftRepo;
	}
	
	public boolean processShifting(String idShift, String nik){
		if(idShift!=null && nik!=null){
			EmployeeShift employeeShift = new EmployeeShift(idShift, nik);
			shiftRepo.save(employeeShift);
		}
		return true;
	}
}
