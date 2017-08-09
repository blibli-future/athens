package com.blibli.future.service.api;

import java.util.List;

import com.blibli.future.exception.IdNotFoundException;
import com.blibli.future.model.Employee;
import com.blibli.future.vo.ShiftVo;

public interface EmployeeShiftingService {
	Employee assignShiftToEmployee(String shiftId, String nik);
	List<ShiftVo> getAllShiftAvailableByNik(String nik) throws IdNotFoundException;
	void removeShiftFromEmployee(String shiftId, String nik) throws IdNotFoundException;
}
