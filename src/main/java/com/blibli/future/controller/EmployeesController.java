package com.blibli.future.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.blibli.future.model.Employee;
import com.blibli.future.service.api.EmployeeService;
import com.blibli.future.vo.EmployeeVo;

@RestController
public class EmployeesController {
	public static final String BASE_PATH = "/employees";
	private EmployeeService employeeService;
	
	@Autowired
	public EmployeesController(EmployeeService employeeService){
		this.employeeService = employeeService;
	}
	
	@PostMapping(BASE_PATH)
    public ResponseEntity<Employee> Employee(@RequestBody EmployeeVo employeeVo){
    	
        Employee savedEmployee = employeeService.saveEmployee(employeeVo);
        if (savedEmployee!=null){
            return new ResponseEntity<Employee>(savedEmployee, HttpStatus.OK);
        }
            return new ResponseEntity<Employee>(savedEmployee, HttpStatus.BAD_REQUEST);

    }
	
    @GetMapping(BASE_PATH)
    public ResponseEntity<List<Employee>> employeeGetAll(){
        List<Employee> getEmployees =
                employeeService.getAllEmployees();
        if(getEmployees!= null){
            return new ResponseEntity<List<Employee>>(getEmployees, HttpStatus.OK);
        }
        return new ResponseEntity<List<Employee>>(getEmployees, HttpStatus.BAD_REQUEST);
    }

    @PutMapping(BASE_PATH)
    public ResponseEntity<Employee> employeeUpdate(@RequestBody EmployeeVo employeeVo){
    	Employee updatedEmployee =
    			employeeService.updateEmployee(employeeVo);
        if(updatedEmployee!=null){
            return new ResponseEntity<Employee>(updatedEmployee, HttpStatus.OK);
        }
        return new ResponseEntity<Employee>(updatedEmployee, HttpStatus.BAD_REQUEST);

    }
}
