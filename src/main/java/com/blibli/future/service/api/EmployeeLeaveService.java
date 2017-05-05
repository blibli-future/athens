package com.blibli.future.service.api;

import java.time.LocalDate;
import java.util.List;

import com.blibli.future.model.EmployeeLeave;

public interface EmployeeLeaveService {
	public boolean sentLeaveRequest(String nik, String idLeave, LocalDate startDate, LocalDate endDate, String reason);
	public List<EmployeeLeave> getLeaveRequest(String nik);
}
