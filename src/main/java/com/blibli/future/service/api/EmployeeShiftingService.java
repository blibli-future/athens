package com.blibli.future.service.api;

import java.util.List;

import com.blibli.future.model.EmployeeShift;
import com.blibli.future.vo.EmployeeShiftVo;

public interface EmployeeShiftingService {
	public EmployeeShift processShifting(EmployeeShift employeeShift);
	public boolean processUpdateShifting(String idShiftLama, String idShift, String nik);
	public List<EmployeeShift> processGetShifting(String idShift);
}
