package com.blibli.future.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.blibli.future.model.Employee;
import com.blibli.future.model.Shift;
import com.blibli.future.repository.EmployeeRepository;
import com.blibli.future.repository.ShiftRepository;
import com.blibli.future.service.api.EmployeeShiftingService;

public class EmployeeShiftingServiceImpl implements EmployeeShiftingService{

	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	ShiftRepository shiftRepository;
	
	@Override
	public Employee processShifting(String nik, String idShift) {
		Employee emp = employeeRepository.findOneByNik(nik);
		Shift shift = shiftRepository.findOneById(idShift);
		if(emp!=null && shift!=null){
			emp.addShifts(shift);
			Employee shiftAdded = employeeRepository.save(emp);
			if(shiftAdded!=null)
				return shiftAdded;
			//Log save failed dudeh
			return null;
		}
		//Log emp or shift null dudeh
		return null;
	}

	@Override
	public Employee employeeShiftingUpdate(String nik, String newShift, String oldShift) {
		Employee emp = employeeRepository.findOneByNik(nik);
		Shift shiftOld = shiftRepository.findOneById(oldShift);
		Shift shiftNew = shiftRepository.findOneById(newShift);
		if(emp!=null&&shiftOld!=null&&shiftNew!=null){
			emp.deleteShifts(shiftOld);
			emp.addShifts(shiftNew);
			Employee shiftUpdated = employeeRepository.save(emp);
			if(shiftUpdated!=null)
				return shiftUpdated;
			//Log update failed dudeh
			return null;
		}
		//Log emp newshift or oldshift null dudeh
		return null;
	}

	@Override
	public Set<Employee> processGetShifting(String idShift) {
		Shift shift = shiftRepository.findOneById(idShift);
		if(shift!=null){
			return shift.getEmployees();
		}
		//Log shift not found dudeh
		return null;
	}

}
