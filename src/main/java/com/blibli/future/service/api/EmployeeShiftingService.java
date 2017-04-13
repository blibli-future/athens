package com.blibli.future.service.api;

import java.util.List;

import com.blibli.future.model.EmployeeShift;

public interface EmployeeShiftingService {
	public boolean processShifting(String idShift, String nik);
	public boolean processUpdateShifting(String idShiftLama, String idShift, String nik);
	public List<EmployeeShift> processGetShifting(String idShift);
}
