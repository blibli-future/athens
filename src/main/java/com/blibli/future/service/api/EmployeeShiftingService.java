package com.blibli.future.service.api;

import com.blibli.future.exception.IdNotFoundException;
import com.blibli.future.model.Employee;

public interface EmployeeShiftingService {
	Employee assignShiftToEmployee(String shiftId, String nik);
	
	void removeShiftFromEmployee(String shiftId, String nik) throws IdNotFoundException;
}
