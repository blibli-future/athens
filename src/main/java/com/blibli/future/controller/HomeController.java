package com.blibli.future.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.blibli.future.exception.TypeNotFoundException;
import com.blibli.future.service.api.ChartService;
import com.blibli.future.vo.ChartResponse;

@RestController
public class HomeController {
	final String BASE_PATH = "/main";
	final String CHART_PATH = BASE_PATH + "/chart/{dept}/{type}";
	
	private ChartService chartService;
	
	@Autowired
	public HomeController(ChartService chartService){
		this.chartService = chartService;
	}
	
	@GetMapping(CHART_PATH)
	public ResponseEntity<ChartResponse> displayChart(@PathVariable String dept, @PathVariable String type) throws TypeNotFoundException{
		return new ResponseEntity<ChartResponse>(chartService.generateChart(dept, type),HttpStatus.OK);
	}
}
