package com.blibli.future.service.api;

import com.blibli.future.model.Employee;

import java.util.List;

public interface EmployeeService {

	public void saveEmployee (Employee employee);
	public boolean isEmployeeExist(String nik);
	public boolean updateEmployee (Employee employee);
	public List<Employee> getEmployeesByDept (String nameOfDept);
}
