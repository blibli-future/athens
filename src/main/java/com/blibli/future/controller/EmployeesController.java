package com.blibli.future.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.blibli.future.dto.response.ErrorResponse;
import com.blibli.future.exception.IdNotFoundException;
import com.blibli.future.model.Employee;
import com.blibli.future.service.api.EmployeeService;
import com.blibli.future.service.api.EmployeeStatisticService;
import com.blibli.future.vo.EmployeeResponseVo;
import com.blibli.future.vo.SummariesVo;
import com.blibli.future.vo.EmployeeEditRequestVo;
import com.blibli.future.vo.EmployeeRequestVo;

@RestController
public class EmployeesController {
	public static final String BASE_PATH = "/employees";
	public static final String SINGLE_EMPLOYEE_PATH = BASE_PATH + "/{nik}";
	public static final String SUMMARIES_PATH = BASE_PATH + "/{nik}/summaries";
	
	private final EmployeeService employeeService;
	private final EmployeeStatisticService employeeStatisticService;
	
	@Autowired
	public EmployeesController(EmployeeService employeeService, EmployeeStatisticService employeeStatisticService){
		this.employeeService = employeeService;
		this.employeeStatisticService = employeeStatisticService;
	}
	
	@PostMapping(BASE_PATH)
    public ResponseEntity<Employee> saveEmployee(@RequestBody EmployeeRequestVo employeeVo){
    	
        Employee savedEmployee = employeeService.saveEmployee(employeeVo);
        if (savedEmployee!=null){
            return new ResponseEntity<Employee>(savedEmployee, HttpStatus.OK);
        }
            return new ResponseEntity<Employee>(savedEmployee, HttpStatus.BAD_REQUEST);
    }
	
    @GetMapping(BASE_PATH)
    public ResponseEntity<List<EmployeeResponseVo>> getAllEmployees(){
        List<EmployeeResponseVo> getEmployees =
                employeeService.getAllEmployees();
        if(getEmployees!= null){
        	System.out.print(getEmployees.size());
            return new ResponseEntity<List<EmployeeResponseVo>>(getEmployees, HttpStatus.OK);
        }
        return new ResponseEntity<List<EmployeeResponseVo>>(getEmployees, HttpStatus.BAD_REQUEST);
    }
    
    @GetMapping(SINGLE_EMPLOYEE_PATH)
    public ResponseEntity <EmployeeResponseVo> getEmployeeByNik(@PathVariable String nik) throws IdNotFoundException{
        EmployeeResponseVo getEmployee = employeeService.getEmployeeByNik(nik);
        return new ResponseEntity<EmployeeResponseVo>(getEmployee, HttpStatus.OK);
    }

    @PutMapping(SINGLE_EMPLOYEE_PATH)
    public ResponseEntity<Employee> employeeUpdate(@PathVariable String nik, @RequestBody EmployeeEditRequestVo employeeVo) throws IdNotFoundException{
    	Employee updatedEmployee =
    			employeeService.updateEmployee(employeeVo);
        if(updatedEmployee.getNik().equals(nik)){
            return new ResponseEntity<Employee>(updatedEmployee, HttpStatus.OK);
        }
        return new ResponseEntity<Employee>(updatedEmployee, HttpStatus.BAD_REQUEST);

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
