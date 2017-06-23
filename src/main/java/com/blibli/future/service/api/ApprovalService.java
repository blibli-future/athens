package com.blibli.future.service.api;

import com.blibli.future.model.EmployeeAbsencePermit;
import com.blibli.future.model.EmployeeLeave;

public interface ApprovalService {
	public EmployeeAbsencePermit absencePermitApproval(String idAbsencePermit, String nik);
	public EmployeeLeave leaveApproval(String idLeave, String nik);
}
