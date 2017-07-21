package com.blibli.future.controller;

import com.blibli.future.service.api.ReportService;
import com.blibli.future.vo.ReportResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
public class ReportController {
	
	public final String BASE_PATH = "/report";
    
	ReportService reportService;
	
	@Autowired
	ReportController(ReportService reportService){
		this.reportService = reportService;
	}
	
	@GetMapping(BASE_PATH)
	public ResponseEntity<List<ReportResponseVo>> getFullReport(@RequestParam("dept") String dept,
			@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		List<ReportResponseVo> reportRequest = reportService.generateFullReport(dept, LocalDate.parse(startDate, formatter), LocalDate.parse(endDate, formatter));
		return new ResponseEntity<List<ReportResponseVo>>(reportRequest, HttpStatus.OK);
	}
}
