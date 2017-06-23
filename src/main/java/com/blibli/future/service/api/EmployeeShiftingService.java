package com.blibli.future.service.api;

import java.util.Set;

import com.blibli.future.model.Employee;

public interface EmployeeShiftingService {
	public Employee processShifting(String nik, String idShift);
	public Employee employeeShiftingUpdate(String nik, String newShift, String oldShift);
	public Set<Employee> processGetShifting(String idShift);
}
