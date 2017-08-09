package com.blibli.future.service;

import com.blibli.future.exception.IdNotFoundException;
import com.blibli.future.model.Employee;
import com.blibli.future.model.Shift;
import com.blibli.future.repository.EmployeeRepository;
import com.blibli.future.repository.ShiftRepository;
import com.blibli.future.service.api.EmployeeShiftingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public Employee assignShiftToEmployee(String shiftId, String nik) {
		Employee emp = employeeRepository.findOneByNik(nik);
		Shift shift = shiftRepository.findOneById(shiftId);
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


    @Override//TODO: what should it return?
    public void removeShiftFromEmployee(String shiftId, String nik) throws IdNotFoundException {
        Shift shift = shiftRepository.findOneById(shiftId);

        if(shift == null) {
            throw new IdNotFoundException("Shift with ID: " + shiftId + " was not found in the database");
        }

        Employee employee = employeeRepository.findOneByNik(nik);

        if(employee == null) {
            throw new IdNotFoundException("NIK: " + nik + " was not found");
        }

        if(employee.getShifts().contains(shift)) {
            employee.deleteShifts(shift);
        }

        employeeRepository.save(employee);
    }

}
