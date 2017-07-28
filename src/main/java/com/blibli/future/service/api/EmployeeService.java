package com.blibli.future.service.api;

import com.blibli.future.model.Employee;
import com.blibli.future.vo.EmployeeVo;

import java.util.List;

public interface EmployeeService {
	Employee saveEmployee (EmployeeVo employeeVo);
	boolean isEmployeeExist(String nik);
	Employee updateEmployee (EmployeeVo employeeVo);
	List<Employee> getEmployeesByDept (String nameOfDept);
	List<Employee> getAllEmployees ();
}
