package com.blibli.future.service.api;

import java.util.List;

import com.blibli.future.model.Leave;

public interface LeaveService {
	public List<Leave> getLeave(String gender, String maritalStatus, String religion);
}
