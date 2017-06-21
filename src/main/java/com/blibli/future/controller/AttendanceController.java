package com.blibli.future.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blibli.future.enums.Gender;
import com.blibli.future.enums.MaritalStatus;
import com.blibli.future.enums.Religion;
import com.blibli.future.exception.UnreadableFile;
import com.blibli.future.model.Attendance;
import com.blibli.future.model.Employee;
import com.blibli.future.service.api.EmployeeService;
import com.blibli.future.service.api.EmployeeShiftingService;
import com.blibli.future.service.api.EmployeeTappingService;


@RestController
public class AttendanceController {
	
	public final String BASE_PATH = "/employee";
    public final String PATH_SHIFTING = BASE_PATH + "/shift";
    public final String PATH_TAPS = BASE_PATH + "/taps";
    public final String PATH_TAPS_UPLOAD = PATH_TAPS + "/upload";
	
    private EmployeeTappingService employeeTappingService;
    private EmployeeService employeeService;
    private EmployeeShiftingService employeeShiftingService;
    
    @Autowired
    public AttendanceController(EmployeeTappingService employeeTappingService, EmployeeService employeeService, EmployeeShiftingService employeeShiftingService) {
        this.employeeTappingService = employeeTappingService;
        this.employeeService = employeeService;
        this.employeeShiftingService = employeeShiftingService;
    }

    @PostMapping(PATH_TAPS_UPLOAD)
    public ResponseEntity uploadAttendanceFile(@RequestParam("file") MultipartFile file) {
        try {
            employeeTappingService.addTapMachineFile(file);
        } catch (UnreadableFile e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.PRECONDITION_FAILED);
        } catch (DateTimeParseException e) {
            return new ResponseEntity(e.getParsedString(), HttpStatus.PRECONDITION_FAILED);
        }

        //Question: Should it report the successfully created AttendanceData?
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(PATH_TAPS)
    public ResponseEntity employeeTapping(@RequestParam("type") String type,
                                          @RequestParam("tapTime") String tapTime,
                                          @RequestParam("dateTap") String dateTap,
                                          @RequestParam("nik") String nik) {
    	LocalDate dateTapConvert = LocalDate.parse(dateTap);
    	LocalTime tapTimeConvert = LocalTime.parse(tapTime);
    	boolean employeeTapped =
    			employeeTappingService.processTapping(type, nik, dateTapConvert, tapTimeConvert);
        if(employeeTapped) {
            return new ResponseEntity(true, HttpStatus.OK);
        }
        return new ResponseEntity(false, HttpStatus.BAD_REQUEST);
    }
    
    @PutMapping(PATH_TAPS)
    public ResponseEntity employeeTappingUpdate(@RequestParam("type") String type, @RequestParam("tapTime") String tapTime,
    		@RequestParam("dateTap") String dateTap, @RequestParam("nik") String nik) {
    	LocalDate dateTapConvert = LocalDate.parse(dateTap);
    	LocalTime tapTimeConvert = LocalTime.parse(tapTime);
    	boolean employeeUpdated = 
    			employeeTappingService.processUpdateTapping(type, nik, dateTapConvert, tapTimeConvert);
        if(employeeUpdated) {
            return new ResponseEntity(true, HttpStatus.OK);
        }
        return new ResponseEntity(false, HttpStatus.BAD_REQUEST);
    }

    @GetMapping(PATH_TAPS)
    public ResponseEntity<List<Attendance>> employeeTappingGet(@RequestParam("dateStart") String dateStart, 
    		@RequestParam("dateEnd") String dateEnd) {
    	LocalDate dateStartConvert = LocalDate.parse(dateStart);
    	LocalDate dateEndConvert = LocalDate.parse(dateEnd);
    	List<Attendance> employeeTapGetted = 
    			employeeTappingService.processGetTapping(dateStartConvert, dateEndConvert);
        if(employeeTapGetted!=null) {
            return new ResponseEntity(employeeTapGetted, HttpStatus.OK);
        }
        return new ResponseEntity(employeeTapGetted, HttpStatus.BAD_REQUEST);
    }

    @PostMapping(PATH_SHIFTING)
    public ResponseEntity<Employee> employeeShifting(@RequestParam("nik") String nik, @RequestParam("idShift") String idShift) {
    	Employee employeeShifted = employeeShiftingService.processShifting(nik,idShift);
    	if(employeeShifted!=null)
    		return new ResponseEntity<Employee>(employeeShifted, HttpStatus.OK);
    	else
    		return new ResponseEntity<Employee>(employeeShifted, HttpStatus.BAD_REQUEST);
    }

    @PutMapping(PATH_SHIFTING)
    public ResponseEntity<Employee> employeeShiftingUpdate(@RequestParam("nik") String nik, 
    		@RequestParam("newShift") String newShift, @RequestParam("oldShift") String oldShift) {
    	Employee employeeShiftUpdated =
    			employeeShiftingService.employeeShiftingUpdate(nik, newShift, oldShift);
        if(employeeShiftUpdated!=null) {
            return new ResponseEntity<Employee>(employeeShiftUpdated, HttpStatus.OK);
        }
        return new ResponseEntity<Employee>(employeeShiftUpdated, HttpStatus.BAD_REQUEST);
    }

    @GetMapping(PATH_SHIFTING)
    public ResponseEntity<Set<Employee>> employeeShiftingGet(@RequestParam("idShift") String idShift) {
    	Set<Employee> employeeShiftGetted =
    			employeeShiftingService.processGetShifting(idShift);
        if(employeeShiftGetted!=null) {
            return new ResponseEntity<Set<Employee>>(employeeShiftGetted, HttpStatus.OK);
        }
        return new ResponseEntity<Set<Employee>>(employeeShiftGetted, HttpStatus.BAD_REQUEST);
    }
    
    @PostMapping(BASE_PATH)
    public ResponseEntity Employee(@RequestParam("nik") String nik , @RequestParam("fullName") String fullName ,
                                   @RequestParam("chiefNik") String chiefNik, @RequestParam("chiefName") String chiefName,
                                   @RequestParam("chiefPosition") String chiefPosition, @RequestParam("chiefPositionText") String chiefPositionText,
                                   @RequestParam("level") String level, @RequestParam("startWorkingDate") String startWorkingDate,
                                   @RequestParam("endWorkingDate") String endWorkingDate, @RequestParam("gender") String gender,
                                   @RequestParam("maritalStatus") String maritalStatus, @RequestParam("organizationalUnitText") String organizationalUnitText,
                                   @RequestParam ("religion") String religion, @RequestParam("nameOfDept") String nameOfDept,
                                   @RequestParam("position") String position,@RequestParam("status") String status){

    	Boolean statusConvert = Boolean.parseBoolean(status);
    	Gender genderConvert = Gender.valueOf(gender);
        MaritalStatus maritalConvert = MaritalStatus.valueOf(maritalStatus);
        Religion religionConvert = Religion.valueOf(religion);
    	LocalDate endWorkingDateConvert = LocalDate.parse(endWorkingDate);
    	LocalDate startWorkingDateConvert = LocalDate.parse(startWorkingDate);
    	
        Boolean employeeAdded = employeeService.saveEmployee(nik, fullName,genderConvert,position,level,
        		organizationalUnitText,maritalConvert,religionConvert,nameOfDept,chiefNik,
        		chiefName,chiefPosition,chiefPositionText,
        		startWorkingDateConvert,endWorkingDateConvert,statusConvert);
        if (employeeAdded){
            return new ResponseEntity(true, HttpStatus.OK);
        }
            return new ResponseEntity(true, HttpStatus.BAD_REQUEST);

    }
    @GetMapping(BASE_PATH)
    public ResponseEntity<List<Employee>> employeeGetByDepartment(@RequestParam("nameOfDept") String nameOfDept){
        List<Employee> getEmployees =
                employeeService.getEmployeesByDept(nameOfDept);
        if(getEmployees!= null){
            return new ResponseEntity(getEmployees, HttpStatus.OK);
        }
        return new ResponseEntity(getEmployees, HttpStatus.BAD_REQUEST);
    }

    @PutMapping(BASE_PATH)
    public ResponseEntity employeeUpdate(@RequestParam("nik") String nik , @RequestParam("fullName") String fullName ,
            @RequestParam("chiefNik") String chiefNik, @RequestParam("chiefName") String chiefName,
            @RequestParam("chiefPosition") String chiefPosition, @RequestParam("chiefPositionText") String chiefPositionText,
            @RequestParam("level") String level, @RequestParam("startWorkingDate") String startWorkingDate,
            @RequestParam("endWorkingDate") String endWorkingDate, @RequestParam("gender") String gender,
            @RequestParam("maritalStatus") String maritalStatus, @RequestParam("organizationalUnitText") String organizationalUnitText,
            @RequestParam ("religion") String religion, @RequestParam("nameOfDept") String nameOfDept,
            @RequestParam("position") String position,@RequestParam("status") String status){

    	Boolean statusConvert = Boolean.parseBoolean(status);
    	Gender genderConvert = Gender.valueOf(gender);
        MaritalStatus maritalConvert = MaritalStatus.valueOf(maritalStatus);
        Religion religionConvert = Religion.valueOf(religion);
    	LocalDate endWorkingDateConvert = LocalDate.parse(endWorkingDate);
    	LocalDate startWorkingDateConvert = LocalDate.parse(startWorkingDate);
    	Boolean employeeUpdated =
    			employeeService.updateEmployee(nik, fullName,genderConvert,position,level,
    	        		organizationalUnitText,maritalConvert,religionConvert,nameOfDept,chiefNik,
    	        		chiefName,chiefPosition,chiefPositionText,
    	        		startWorkingDateConvert,endWorkingDateConvert,statusConvert);
        if(employeeUpdated){
            return new ResponseEntity(true, HttpStatus.OK);
        }
        return new ResponseEntity(false, HttpStatus.BAD_REQUEST);

    }
    }
