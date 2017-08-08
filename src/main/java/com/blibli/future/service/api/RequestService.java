package com.blibli.future.service.api;

import java.util.List;

import com.blibli.future.exception.IdNotFoundException;
import com.blibli.future.exception.TypeNotFoundException;
import com.blibli.future.vo.PermissionRequestVo;
import com.blibli.future.vo.PermissionResponseVo;

public interface RequestService {
	public PermissionRequestVo createRequest(String nik, String type, PermissionRequestVo permissionRequestVo) throws IdNotFoundException, TypeNotFoundException;
	public List<PermissionResponseVo> getEmployeeRequestHistories(String nik) throws IdNotFoundException;
}
