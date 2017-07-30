package com.blibli.future.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blibli.future.enums.Gender;
import com.blibli.future.enums.MaritalStatus;
import com.blibli.future.enums.Religion;
import com.blibli.future.model.EmployeeAbsencePermit;
import com.blibli.future.model.EmployeeLeave;
import com.blibli.future.model.Leave;
import com.blibli.future.service.api.EmployeeAbsencePermitService;
import com.blibli.future.service.api.EmployeeLeaveService;
import com.blibli.future.service.api.LeaveService;
import com.blibli.future.vo.EmployeeAbsencePermitVo;
import com.blibli.future.vo.EmployeeLeaveVo;

@RestController
public class RequestController {
	public static final String BASE_PATH = "/request";
    public static final String PATH_LEAVE = BASE_PATH + "/leave";
    public static final String PATH_LISTING_LEAVE = PATH_LEAVE + "/list";
    public static final String PATH_ABSENCE_PERMIT = BASE_PATH + "/absencepermit";
	
	private EmployeeLeaveService employeeLeaveService;
	private EmployeeAbsencePermitService employeeAbsencePermitService;
	private LeaveService leaveService;
	
	@Autowired
	RequestController(EmployeeLeaveService employeeLeaveService, EmployeeAbsencePermitService employeeAbsencePermitService,
			LeaveService leaveService){
		this.employeeLeaveService = employeeLeaveService;
		this.employeeAbsencePermitService = employeeAbsencePermitService;
		this.leaveService = leaveService;
	}
	
	@PostMapping(PATH_LEAVE)
	public ResponseEntity<EmployeeLeave> sentLeaveRequest(@RequestBody EmployeeLeaveVo employeeLeaveVo){
    	EmployeeLeave requested = employeeLeaveService.sentLeaveRequest(employeeLeaveVo);
    	if(requested!=null)
    	{
    		return new ResponseEntity<EmployeeLeave>(requested, HttpStatus.OK);
    	}
    	else
    	{
    		return new ResponseEntity<EmployeeLeave>(requested, HttpStatus.BAD_REQUEST);
    	}
	}
	
	@PutMapping(PATH_LEAVE)
	public ResponseEntity<EmployeeLeave> updateLeaveRequest(@RequestBody EmployeeLeaveVo employeeLeaveVo){
		EmployeeLeave requested = employeeLeaveService.updateLeaveRequest(employeeLeaveVo);
    	if(requested!=null)
    	{
    		return new ResponseEntity<EmployeeLeave>(requested, HttpStatus.OK);
    	}
    	else
    	{
    		return new ResponseEntity<EmployeeLeave>(requested, HttpStatus.BAD_REQUEST);
    	}
	}
	
	@GetMapping(PATH_LEAVE)
	public ResponseEntity<List<EmployeeLeave>> getLeaveRequest(@RequestParam("nik") String nik){
		List<EmployeeLeave> employeeLeaveGetted = employeeLeaveService.getLeaveRequest(nik);
    	if(employeeLeaveGetted!=null)
    	{
    		return new ResponseEntity<List<EmployeeLeave>>(employeeLeaveGetted, HttpStatus.OK);
    	}
    	else
    	{
    		return new ResponseEntity<List<EmployeeLeave>>(employeeLeaveGetted, HttpStatus.BAD_REQUEST);
    	}
	}
	
	@PostMapping(PATH_ABSENCE_PERMIT)
	public ResponseEntity<EmployeeAbsencePermit> sentAbsencePermitRequest(@RequestBody EmployeeAbsencePermitVo employeeAbsencePermitVo){
    	EmployeeAbsencePermit requested = employeeAbsencePermitService.sentAbsencePermitRequest(employeeAbsencePermitVo);
    	if(requested!=null)
    	{
    		return new ResponseEntity<EmployeeAbsencePermit>(requested, HttpStatus.OK);
    	}
    	else
    	{
    		return new ResponseEntity<EmployeeAbsencePermit>(requested, HttpStatus.BAD_REQUEST);
    	}
	}
	
	@GetMapping(PATH_ABSENCE_PERMIT)
	public ResponseEntity<List<EmployeeAbsencePermit>> getAbsencePermitRequest(@RequestParam("nik") String nik){
		List<EmployeeAbsencePermit> requested = employeeAbsencePermitService.getAbsencePermitRequest(nik);
    	if(requested!=null)
    	{
    		return new ResponseEntity<List<EmployeeAbsencePermit>>(requested, HttpStatus.OK);
    	}
    	else
    	{
    		return new ResponseEntity<List<EmployeeAbsencePermit>>(requested, HttpStatus.BAD_REQUEST);
    	}
	}
	
	@PutMapping(PATH_ABSENCE_PERMIT)
	public ResponseEntity<EmployeeAbsencePermit> updateAbsencePermitRequest(@RequestBody EmployeeAbsencePermitVo employeeAbsencePermitVo){
		EmployeeAbsencePermit requested = employeeAbsencePermitService.updateAbsencePermitRequest(employeeAbsencePermitVo);
    	if(requested!=null)
    	{
    		return new ResponseEntity<EmployeeAbsencePermit>(requested, HttpStatus.OK);
    	}
    	else
    	{
    		return new ResponseEntity<EmployeeAbsencePermit>(requested, HttpStatus.BAD_REQUEST);
    	}
	}
	
	@GetMapping(PATH_LISTING_LEAVE)
	public ResponseEntity<List<Leave>> getLeave(@RequestParam("gender") String gender,
			@RequestParam("maritalStatus") String maritalStatus,
			@RequestParam("religion") String religion){
    	
		List<Leave> requested = leaveService.getLeave(Gender.valueOf(gender), MaritalStatus.valueOf(maritalStatus), Religion.valueOf(religion));
    	if(requested!=null)
    	{
    		return new ResponseEntity<List<Leave>>(requested, HttpStatus.OK);
    	}
    	else
    	{
    		return new ResponseEntity<List<Leave>>(requested, HttpStatus.BAD_REQUEST);
    	}
	}
}
