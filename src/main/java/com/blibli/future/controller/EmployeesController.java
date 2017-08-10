package com.blibli.future.controller;

import com.blibli.future.dto.response.ErrorResponse;
import com.blibli.future.exception.IdNotFoundException;
import com.blibli.future.model.Employee;
import com.blibli.future.service.api.EmployeeService;
import com.blibli.future.service.api.EmployeeShiftingService;
import com.blibli.future.service.api.EmployeeStatisticService;
import com.blibli.future.vo.*;

import org.jboss.logging.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class EmployeesController {
	public static final String BASE_PATH = "/employees";
	public static final String SINGLE_EMPLOYEE_PATH = BASE_PATH + "/{nik}";
	public static final String SUMMARIES_PATH = BASE_PATH + "/{nik}/summaries";
    public final String EMPLOYEE_SHIFT_PATH = SINGLE_EMPLOYEE_PATH + "/shifts";
    public final String EMPLOYEE_SHIFTING_PATH = SINGLE_EMPLOYEE_PATH + "/{shiftId}/{type}";

    private final EmployeeService employeeService;
    private final EmployeeShiftingService employeeShiftingService;
	private final EmployeeStatisticService employeeStatisticService;
	
	@Autowired
	public EmployeesController(EmployeeService employeeService, EmployeeStatisticService employeeStatisticService, EmployeeShiftingService employeeShiftingService){
		this.employeeService = employeeService;
		this.employeeStatisticService = employeeStatisticService;
		this.employeeShiftingService = employeeShiftingService;
	}
	
	@PostMapping(BASE_PATH)
    public ResponseEntity<String> saveEmployee(@RequestBody EmployeeRequestVo employeeVo){
    	
        Employee savedEmployee = employeeService.saveEmployee(employeeVo);
        if (savedEmployee!=null){
            return new ResponseEntity<String>("Employee " +savedEmployee.getNik()+" saved", HttpStatus.OK);
        }
            return new ResponseEntity<String>("Save Failed", HttpStatus.BAD_REQUEST);
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
    public ResponseEntity<String> employeeUpdate(@PathVariable String nik, @RequestBody EmployeeEditRequestVo employeeVo) throws IdNotFoundException{
    	Employee updatedEmployee =
    			employeeService.updateEmployee(employeeVo);
        if(updatedEmployee.getNik().equals(nik)){
            return new ResponseEntity<String>("Employee: "+nik+" updated", HttpStatus.OK);
        }
        return new ResponseEntity<String>("Update Employee "+nik+" Failed", HttpStatus.BAD_REQUEST);

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

    @GetMapping(value = EMPLOYEE_SHIFT_PATH)
    public ResponseEntity<List<ShiftVo>> getAssignedShift(@PathVariable String nik) throws IdNotFoundException {
        return new ResponseEntity<List<ShiftVo>>(employeeShiftingService.getAllShiftAvailableByNik(nik), HttpStatus.OK);
    }
    
    @PostMapping(value = EMPLOYEE_SHIFTING_PATH)
    public ResponseEntity<String> AssignShift(@PathVariable String nik, @PathVariable String shiftId, @PathVariable String type) throws IdNotFoundException {
    	if(type.equals("add")){
    		employeeShiftingService.assignShiftToEmployee(shiftId, nik);
    	}
    	else if(type.equals("remove")){
    		employeeShiftingService.removeShiftFromEmployee(shiftId, nik);
    	}
    	return new ResponseEntity<String>("Shifting Processed", HttpStatus.OK);
    }
}
