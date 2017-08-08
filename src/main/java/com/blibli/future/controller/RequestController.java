package com.blibli.future.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.blibli.future.exception.IdNotFoundException;
import com.blibli.future.exception.TypeNotFoundException;
import com.blibli.future.model.Leave;
import com.blibli.future.service.api.LeaveService;
import com.blibli.future.service.api.RequestService;
import com.blibli.future.vo.LeaveResponseVo;
import com.blibli.future.vo.PermissionRequestVo;
import com.blibli.future.vo.PermissionResponseVo;

@RestController
public class RequestController {
	public static final String BASE_PATH = "/requests";
    public static final String PATH_LISTING_LEAVE = BASE_PATH + "/{nik}/leave-right";
    public static final String PATH_CREATE_REQUEST = BASE_PATH + "/{nik}/{type}";
    public static final String PATH_HISTORIES = BASE_PATH + "/{nik}";
	
    private RequestService requestService;
	private LeaveService leaveService;
	
	@Autowired
	RequestController(RequestService requestService, LeaveService leaveService){
		this.requestService = requestService;
		this.leaveService = leaveService;
	}
	
	@PostMapping(PATH_CREATE_REQUEST)
	public ResponseEntity<String> createRequest(@PathVariable String nik, @PathVariable String type,
			@RequestBody PermissionRequestVo permissionRequestVo) throws IdNotFoundException, TypeNotFoundException{
    	requestService.createRequest(nik, type, permissionRequestVo);
		return new ResponseEntity<String>("id: " + permissionRequestVo.getRequestKey() + " processed", HttpStatus.OK);
	}
	
	@GetMapping(PATH_HISTORIES)
	public ResponseEntity<List<PermissionResponseVo>> getEmployeeRequestHistories(@PathVariable String nik) throws IdNotFoundException{
		List<PermissionResponseVo> requested = requestService.getEmployeeRequestHistories(nik);
    	return new ResponseEntity<List<PermissionResponseVo>>(requested, HttpStatus.OK);
	}
	
	@GetMapping(PATH_LISTING_LEAVE)
	public ResponseEntity<List<LeaveResponseVo>> getLeave(@PathVariable String nik) throws IdNotFoundException{
		List<LeaveResponseVo> requested = leaveService.getLeave(nik);
    	return new ResponseEntity<List<LeaveResponseVo>>(requested, HttpStatus.OK);
	}
}
