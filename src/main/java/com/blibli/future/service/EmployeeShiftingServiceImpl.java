package com.blibli.future.service;

import com.blibli.future.exception.IdNotFoundException;
import com.blibli.future.model.Employee;
import com.blibli.future.model.Shift;
import com.blibli.future.repository.EmployeeRepository;
import com.blibli.future.repository.ShiftRepository;
import com.blibli.future.service.api.EmployeeService;
import com.blibli.future.service.api.EmployeeShiftingService;
import com.blibli.future.vo.ShiftVo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeShiftingServiceImpl implements EmployeeShiftingService{
	private final EmployeeRepository employeeRepository;
	private final ShiftRepository shiftRepository;
	private EmployeeService employeeService;

    @Autowired
    public EmployeeShiftingServiceImpl(EmployeeRepository employeeRepository, ShiftRepository shiftRepository, EmployeeService employeeService) {
        this.employeeRepository = employeeRepository;
        this.shiftRepository = shiftRepository;
        this.employeeService = employeeService;
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



	@Override
	public List<ShiftVo> getAllShiftAvailableByNik(String nik) throws IdNotFoundException {
		Employee emp = employeeRepository.findOneByNik(nik);
		List<ShiftVo> result = new ArrayList<>();
		List<ShiftVo> employeeShifts = employeeService.getAssignedShiftsVo(nik);
		List<ShiftVo> allShifts = shiftRepository.findAllByDept(emp.getNameOfDept());
		System.out.println(allShifts.size());
		if(employeeShifts.size()!=0){
			for(ShiftVo allShift:allShifts){
				for(ShiftVo employeeShift:employeeShifts){
					if(allShift==employeeShift)
					{
						allShift = employeeShift;
					}
					result.add(allShift);
				}
			}
			return result;
		}
		return allShifts;
		
	}

}
