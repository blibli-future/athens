package com.blibli.future.controller;

import com.blibli.future.dto.response.ErrorResponse;
import com.blibli.future.exception.IdNotFoundException;
import com.blibli.future.service.api.EmployeeService;
import com.blibli.future.service.api.EmployeeStatisticService;
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
    private final EmployeeStatisticService employeeStatisticService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, EmployeeStatisticService employeeStatisticService) {
        this.employeeService = employeeService;
        this.employeeStatisticService = employeeStatisticService;
    }

    @GetMapping(SUMMARIES_PATH)
    public ResponseEntity retrieveEmployeeSummary(@PathVariable String nik)  {
        SummariesVo response;
        try {
            response = employeeStatisticService.generateSummaries(nik);
        } catch (IdNotFoundException e) {
            return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
