package com.blibli.future.service.api;

import java.util.List;

import com.blibli.future.model.EmployeeAbsencePermit;
import com.blibli.future.model.EmployeeLeave;
import com.blibli.future.vo.ApprovalResponseVo;

public interface ApprovalService {
	public EmployeeAbsencePermit processAbsencePermit(String idAbsencePermit, String chiefNik, boolean isApproved);
	public EmployeeLeave processLeave(String idLeave, String chiefNik, boolean isApproved);
	public List<ApprovalResponseVo> getUnapprovedRequests(String chiefNik);
	public List<ApprovalResponseVo> getRequestHistories(String chiefNik);
}
