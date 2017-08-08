package com.blibli.future.service;

import com.blibli.future.model.Employee;
import com.blibli.future.model.Shift;
import com.blibli.future.repository.EmployeeRepository;
import com.blibli.future.repository.ShiftRepository;
import com.blibli.future.service.api.EmployeeShiftingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class EmployeeShiftingServiceImpl implements EmployeeShiftingService{
	private final EmployeeRepository employeeRepository;
	private final ShiftRepository shiftRepository;

    @Autowired
    public EmployeeShiftingServiceImpl(EmployeeRepository employeeRepository, ShiftRepository shiftRepository) {
        this.employeeRepository = employeeRepository;
        this.shiftRepository = shiftRepository;
    }

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
