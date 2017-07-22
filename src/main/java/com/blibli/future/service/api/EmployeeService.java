package com.blibli.future.service.api;

import com.blibli.future.enums.Gender;
import com.blibli.future.enums.MaritalStatus;
import com.blibli.future.enums.Religion;
import com.blibli.future.model.Employee;
import com.blibli.future.vo.EmployeeVo;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeService {

	public Employee saveEmployee (EmployeeVo employeeVo);
	public boolean isEmployeeExist(String nik);
	public Employee updateEmployee (EmployeeVo employeeVo);
	public List<Employee> getEmployeesByDept (String nameOfDept);
	public List<Employee> getAllEmployees ();
}
