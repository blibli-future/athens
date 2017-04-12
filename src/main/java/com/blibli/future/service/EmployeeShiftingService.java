package com.blibli.future.service;

import java.util.ArrayList;
import java.util.List;

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
			return true;
		}
		return false;
	}
	
	public boolean processUpdateShifting(String idShiftLama, String idShift, String nik){
		if(idShift!=null && nik!=null){
			EmployeeShift employeeShift = shiftRepo.findOneByNikAndIdshift(nik, idShiftLama);
			employeeShift.setIdShift(idShift);
			shiftRepo.save(employeeShift);
			return true;
		}
		return false;
	}
	
	public List<EmployeeShift> processGetShifting(String idShift){
		List<EmployeeShift> listEmployeeShift = new ArrayList<>();
		listEmployeeShift = shiftRepo.findByIdshift(idShift);
		if(listEmployeeShift!=null){
			return listEmployeeShift;
		}
		return null;
	}
}
