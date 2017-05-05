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
import com.blibli.future.service.api.EmployeeLeaveService;

@RestController
public class RequestController {
	private EmployeeLeaveService requestService;
	
	@Autowired
	RequestController(EmployeeLeaveService requestService){
		this.requestService = requestService;
	}
	
	@PostMapping()
	public ResponseEntity sentLeaveRequest(@RequestParam("nik") String nik,
            @RequestParam("idLeave") String idLeave,
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate,
            @RequestParam("reason") String reason){
		LocalDate startDateConvert = LocalDate.parse(startDate);
    	LocalDate endDateConvert = LocalDate.parse(endDate);
    	
    	boolean Requested = requestService.sentRequest(nik, idLeave, startDateConvert, endDateConvert, reason);
    	if(Requested)
    	{
    		return new ResponseEntity(true, HttpStatus.OK);
    	}
    	else
    	{
    		return new ResponseEntity(false, HttpStatus.BAD_REQUEST);
    	}
	}
	
	@GetMapping()
	public ResponseEntity<List<EmployeeLeave>> getLeaveRequest(@RequestParam("nik") String nik){
    	
		List<EmployeeLeave> employeeLeaveGetted ;
    	if(true)
    	{
    		return new ResponseEntity(true, HttpStatus.OK);
    	}
    	else
    	{
    		return new ResponseEntity(false, HttpStatus.BAD_REQUEST);
    	}
	}
}
