package com.blibli.future.service.api;

import com.blibli.future.exception.IdNotFoundException;
import com.blibli.future.model.Employee;

import java.util.Set;

public interface EmployeeShiftingService {
	Employee assignShiftToEmployee(String shiftId, String nik);
	Employee employeeShiftingUpdate(String nik, String newShift, String oldShift);
	Set<Employee> processGetShifting(String idShift);

	void removeShiftFromEmployee(String shiftId, String nik) throws IdNotFoundException;
}
