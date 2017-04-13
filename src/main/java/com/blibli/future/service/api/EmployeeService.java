package com.blibli.future.service.api;

import com.blibli.future.model.Employee;

public interface EmployeeService {
	public void deleteEmployee(String nik);
	public void saveEmployee (Employee employee);
	public boolean isEmployeeExist(String nik);
	public void updateEmployee (Employee employee);
}
