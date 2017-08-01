package com.blibli.future.service.api;

import com.blibli.future.model.Employee;
import com.blibli.future.vo.EmployeeResponseVo;
import com.blibli.future.vo.EmployeeRequestVo;

import java.util.List;

public interface EmployeeService {

	public Employee saveEmployee (EmployeeRequestVo employeeVo);
	public boolean isEmployeeExist(String nik);
	public Employee updateEmployee (EmployeeRequestVo employeeVo);
	public List<Employee> getEmployeesByDept (String nameOfDept);
	public List<EmployeeResponseVo> getAllEmployees ();
}
