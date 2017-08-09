package com.blibli.future.service.api;

import com.blibli.future.exception.IdNotFoundException;
import com.blibli.future.model.Employee;
import com.blibli.future.model.Shift;
import com.blibli.future.vo.EmployeeEditRequestVo;
import com.blibli.future.vo.EmployeeRequestVo;
import com.blibli.future.vo.EmployeeResponseVo;
import com.blibli.future.vo.ShiftVo;

import java.util.List;
import java.util.Set;

public interface EmployeeService {
	Employee saveEmployee (EmployeeRequestVo employeeVo);
	boolean isEmployeeExist(String nik);
	Employee updateEmployee (EmployeeEditRequestVo employeeVo) throws IdNotFoundException;
	List<EmployeeResponseVo> getAllEmployees();
	EmployeeResponseVo getEmployeeByNik(String nik) throws IdNotFoundException;

    Set<ShiftVo> getAssignedShifts(String nik) throws IdNotFoundException;
    List<Shift> getAssignedShiftsList(String nik) throws IdNotFoundException;
}