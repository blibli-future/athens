package com.blibli.future.controller;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blibli.future.service.api.LeaveService;

@RestController
public class RequestController {
	private LeaveService requestService;
	
	@Autowired
	RequestController(LeaveService requestService){
		this.requestService = requestService;
	}
	
	@PostMapping()
	public ResponseEntity sentRequest(@RequestParam("nik") String nik,
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
}
