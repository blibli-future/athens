package com.blibli.future.service.api;

import java.util.List;

import com.blibli.future.model.EmployeeAbsencePermit;
import com.blibli.future.vo.EmployeeAbsencePermitVo;

public interface EmployeeAbsencePermitService {
	public EmployeeAbsencePermit sentAbsencePermitRequest(EmployeeAbsencePermitVo employeeAbsencePermit);
	public EmployeeAbsencePermit updateAbsencePermitRequest(EmployeeAbsencePermitVo employeeAbsencePermit);
	public List<EmployeeAbsencePermit> getAbsencePermitRequest(String nik);
}
