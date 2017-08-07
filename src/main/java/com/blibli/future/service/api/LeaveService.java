package com.blibli.future.service.api;

import java.util.List;

import com.blibli.future.enums.Gender;
import com.blibli.future.enums.MaritalStatus;
import com.blibli.future.enums.Religion;
import com.blibli.future.exception.IdNotFoundException;
import com.blibli.future.model.Leave;
import com.blibli.future.vo.LeaveResponseVo;

public interface LeaveService {
	List<LeaveResponseVo> getLeave(String nik) throws IdNotFoundException;
}