package com.blibli.future.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blibli.future.model.EmployeeAbsencePermit;
import com.blibli.future.model.EmployeeLeave;
import com.blibli.future.service.api.EmployeeAbsencePermitService;
import com.blibli.future.service.api.EmployeeLeaveService;

@RestController
public class RequestController {
	private EmployeeLeaveService employeeLeaveService;
	private EmployeeAbsencePermitService employeeAbsencePermitService;
	
	@Autowired
	RequestController(EmployeeLeaveService employeeLeaveService, EmployeeAbsencePermitService employeeAbsencePermitService){
		this.employeeLeaveService = employeeLeaveService;
		this.employeeAbsencePermitService = employeeAbsencePermitService;
	}
	
	@PostMapping()
	public ResponseEntity sentLeaveRequest(@RequestParam("nik") String nik,
            @RequestParam("idLeave") String idLeave,
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate,
            @RequestParam("reason") String reason){
		LocalDate startDateConvert = LocalDate.parse(startDate);
    	LocalDate endDateConvert = LocalDate.parse(endDate);
    	
    	boolean Requested = employeeLeaveService.sentLeaveRequest(nik, idLeave, startDateConvert, endDateConvert, reason);
    	if(Requested)
    	{
    		return new ResponseEntity(true, HttpStatus.OK);
    	}
    	else
    	{
    		return new ResponseEntity(false, HttpStatus.BAD_REQUEST);
    	}
	}
	
	@GetMapping() //belum aku sertakan tahun
	public ResponseEntity<List<EmployeeLeave>> getLeaveRequest(@RequestParam("nik") String nik){
    	
		List<EmployeeLeave> employeeLeaveGetted = employeeLeaveService.getLeaveRequest(nik);
    	if(employeeLeaveGetted!=null)
    	{
    		return new ResponseEntity(employeeLeaveGetted, HttpStatus.OK);
    	}
    	else
    	{
    		return new ResponseEntity(employeeLeaveGetted, HttpStatus.BAD_REQUEST);
    	}
	}
	
	@PostMapping()
	public ResponseEntity sentAbsencePermitRequest(@RequestParam("nik") String nik,
            @RequestParam("idAbsencePermit") String idAbsencePermit,
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate,
            @RequestParam("reason") String reason){
		LocalDate startDateConvert = LocalDate.parse(startDate);
    	LocalDate endDateConvert = LocalDate.parse(endDate);
    	
    	boolean Requested = employeeAbsencePermitService.sentAbsencePermitRequest(nik, idAbsencePermit, startDateConvert, endDateConvert, reason);
    	if(Requested)
    	{
    		return new ResponseEntity(true, HttpStatus.OK);
    	}
    	else
    	{
    		return new ResponseEntity(false, HttpStatus.BAD_REQUEST);
    	}
	}
	
	@GetMapping() //belum aku sertakan tahun
	public ResponseEntity<List<EmployeeAbsencePermit>> getAbsencePermitRequest(@RequestParam("nik") String nik){
    	
		List<EmployeeAbsencePermit> employeeAbsencePermitGetted = employeeAbsencePermitService.getAbsencePermitRequest(nik);
    	if(employeeAbsencePermitGetted!=null)
    	{
    		return new ResponseEntity(employeeAbsencePermitGetted, HttpStatus.OK);
    	}
    	else
    	{
    		return new ResponseEntity(employeeAbsencePermitGetted, HttpStatus.BAD_REQUEST);
    	}
	}
}
