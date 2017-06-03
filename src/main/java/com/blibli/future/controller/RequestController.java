package com.blibli.future.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

@RestController
public class RequestController {
	private final String base = "/request/";
	private final String leave = "leave/";
	private final String leaveListing = "leave/list/";
	private final String absencepermit = "absencepermit/";
	
	//private EmployeeLeaveService employeeLeaveService;
	//private EmployeeAbsencePermitService employeeAbsencePermitService;
	private LeaveService leaveService;
	
	@Autowired
	RequestController(EmployeeLeaveService employeeLeaveService, EmployeeAbsencePermitService employeeAbsencePermitService,
			LeaveService leaveService){
		//this.employeeLeaveService = employeeLeaveService;
		//this.employeeAbsencePermitService = employeeAbsencePermitService;
		this.leaveService = leaveService;
	}
	
//	@PostMapping(base+leave)
//	public ResponseEntity sentLeaveRequest(@RequestParam("nik") String nik,
//            @RequestParam("idLeave") String idLeave,
//            @RequestParam("startDate") String startDate,
//            @RequestParam("endDate") String endDate,
//            @RequestParam("reason") String reason){
//		LocalDate startDateConvert = LocalDate.parse(startDate);
//    	LocalDate endDateConvert = LocalDate.parse(endDate);
//    	
//    	boolean Requested = employeeLeaveService.sentLeaveRequest(nik, idLeave, startDateConvert, endDateConvert, reason);
//    	if(Requested)
//    	{
//    		return new ResponseEntity(true, HttpStatus.OK);
//    	}
//    	else
//    	{
//    		return new ResponseEntity(false, HttpStatus.BAD_REQUEST);
//    	}
//	}
	
//	@PutMapping(base+leave)
//	public ResponseEntity updateLeaveRequest(@RequestParam("id") String id ,@RequestParam("nik") String nik,
//            @RequestParam("idLeave") String idLeave,
//            @RequestParam("startDate") String startDate,
//            @RequestParam("endDate") String endDate,
//            @RequestParam("reason") String reason){
//		LocalDate startDateConvert = LocalDate.parse(startDate);
//    	LocalDate endDateConvert = LocalDate.parse(endDate);
//    	
//    	boolean Requested = employeeLeaveService.updateLeaveRequest(id, nik, idLeave, startDateConvert, endDateConvert, reason);
//    	if(Requested)
//    	{
//    		return new ResponseEntity(true, HttpStatus.OK);
//    	}
//    	else
//    	{
//    		return new ResponseEntity(false, HttpStatus.BAD_REQUEST);
//    	}
//	}
//	
//	@GetMapping(base+leave)
//	public ResponseEntity<List<EmployeeLeave>> getLeaveRequest(@RequestParam("nik") String nik){
//    	
//		List<EmployeeLeave> employeeLeaveGetted = employeeLeaveService.getLeaveRequest(nik);
//    	if(employeeLeaveGetted!=null)
//    	{
//    		return new ResponseEntity(employeeLeaveGetted, HttpStatus.OK);
//    	}
//    	else
//    	{
//    		return new ResponseEntity(employeeLeaveGetted, HttpStatus.BAD_REQUEST);
//    	}
//	}
//	
//	@PostMapping(base+absencepermit)
//	public ResponseEntity sentAbsencePermitRequest(@RequestParam("nik") String nik,
//            @RequestParam("idAbsencePermit") String idAbsencePermit,
//            @RequestParam("startDate") String startDate,
//            @RequestParam("endDate") String endDate,
//            @RequestParam("reason") String reason){
//		LocalDate startDateConvert = LocalDate.parse(startDate);
//    	LocalDate endDateConvert = LocalDate.parse(endDate);
//    	
//    	boolean Requested = employeeAbsencePermitService.sentAbsencePermitRequest(nik, idAbsencePermit, startDateConvert, endDateConvert, reason);
//    	if(Requested)
//    	{
//    		return new ResponseEntity(true, HttpStatus.OK);
//    	}
//    	else
//    	{
//    		return new ResponseEntity(false, HttpStatus.BAD_REQUEST);
//    	}
//	}
//	
//	@GetMapping(base+absencepermit)
//	public ResponseEntity<List<EmployeeAbsencePermit>> getAbsencePermitRequest(@RequestParam("nik") String nik){
//    	
//		List<EmployeeAbsencePermit> employeeAbsencePermitGetted = employeeAbsencePermitService.getAbsencePermitRequest(nik);
//    	if(employeeAbsencePermitGetted!=null)
//    	{
//    		return new ResponseEntity(employeeAbsencePermitGetted, HttpStatus.OK);
//    	}
//    	else
//    	{
//    		return new ResponseEntity(employeeAbsencePermitGetted, HttpStatus.BAD_REQUEST);
//    	}
//	}
//	
//	@PutMapping(base+absencepermit)
//	public ResponseEntity updateAbsencePermitRequest(@RequestParam("id") String id ,@RequestParam("nik") String nik,
//            @RequestParam("idAbsencePermit") String idAbsencePermit,
//            @RequestParam("startDate") String startDate,
//            @RequestParam("endDate") String endDate,
//            @RequestParam("reason") String reason){
//		LocalDate startDateConvert = LocalDate.parse(startDate);
//    	LocalDate endDateConvert = LocalDate.parse(endDate);
//    	
//    	boolean Requested = employeeAbsencePermitService.updateAbsencePermitRequest(id, nik, idAbsencePermit, startDateConvert, endDateConvert, reason);
//    	if(Requested)
//    	{
//    		return new ResponseEntity(true, HttpStatus.OK);
//    	}
//    	else
//    	{
//    		return new ResponseEntity(false, HttpStatus.BAD_REQUEST);
//    	}
//	}
//	
//	@GetMapping(base+leaveListing)
//	public ResponseEntity<List<Leave>> getLeave(@RequestParam("gender") String gender,
//			@RequestParam("maritalStatus") String maritalStatus,
//			@RequestParam("religion") String religion){
//    	
//		List<Leave> leaveGetted = leaveService.getLeave(Gender.valueOf(gender), MaritalStatus.valueOf(maritalStatus), Religion.valueOf(religion));
//    	if(leaveGetted!=null)
//    	{
//    		return new ResponseEntity(leaveGetted, HttpStatus.OK);
//    	}
//    	else
//    	{
//    		return new ResponseEntity(leaveGetted, HttpStatus.BAD_REQUEST);
//    	}
//	}
}
