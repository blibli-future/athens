package com.blibli.future.service.api;

import java.util.List;

import com.blibli.future.enums.Gender;
import com.blibli.future.enums.MaritalStatus;
import com.blibli.future.enums.Religion;
import com.blibli.future.model.Leave;

public interface LeaveService {
	List<Leave> getLeave(Gender gender, MaritalStatus maritalStatus, Religion religion);
}
