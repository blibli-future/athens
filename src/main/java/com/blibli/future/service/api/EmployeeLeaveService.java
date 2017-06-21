package com.blibli.future.service.api;

import java.time.LocalDate;
import java.util.List;

import com.blibli.future.model.EmployeeLeave;
import com.blibli.future.vo.EmployeeLeaveVo;

public interface EmployeeLeaveService {
	public EmployeeLeave sentLeaveRequest(EmployeeLeaveVo employeeLeaveVo);
	public List<EmployeeLeave> getLeaveRequest(String nik);
	public EmployeeLeave updateLeaveRequest(EmployeeLeaveVo employeeLeaveVo);
}
