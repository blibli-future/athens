package com.blibli.future.service.api;

import java.util.List;

import com.blibli.future.exception.IdNotFoundException;
import com.blibli.future.model.EmployeeAbsencePermit;
import com.blibli.future.model.EmployeeLeave;
import com.blibli.future.vo.PermissionResponseVo;

public interface ApprovalService {
	public EmployeeAbsencePermit processAbsencePermit(String idAbsencePermit, String chiefNik, boolean isApproved) throws IdNotFoundException;
	public EmployeeLeave processLeave(String idLeave, String chiefNik, boolean isApproved) throws IdNotFoundException;
	public List<PermissionResponseVo> getUnapprovedRequests(String chiefNik);
	public List<PermissionResponseVo> getRequestHistories(String chiefNik);
}
