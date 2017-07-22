package com.blibli.future.controller;

import com.blibli.future.service.api.EmployeeService;
import com.blibli.future.vo.SummariesVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
    public final String SUMMARIES_PATH = "/employees/{nik}/summaries";

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(SUMMARIES_PATH)
    public ResponseEntity<SummariesVo> retrieveEmployeeSummary(@PathVariable String nik) {
        SummariesVo response = this.employeeService.generateSummaries(nik);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
