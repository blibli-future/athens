package com.blibli.future.controller;

import com.blibli.future.exception.IdNotFoundException;
import com.blibli.future.exception.UnreadableFile;
import com.blibli.future.model.Attendance;
import com.blibli.future.model.Employee;
import com.blibli.future.service.api.EmployeeService;
import com.blibli.future.service.api.EmployeeShiftingService;
import com.blibli.future.service.api.EmployeeTappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;


@RestController
public class AttendanceController {
    public final String PATH_SHIFTING = EmployeesController.BASE_PATH + "/shift";
    public final String PATH_TAPS = EmployeesController.BASE_PATH + "/taps";
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
    public ResponseEntity uploadAttendanceFile(@RequestParam("file") MultipartFile file) throws IdNotFoundException{
        try {
            employeeTappingService.addTapMachineFile(file);
        } catch (UnreadableFile e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (DateTimeParseException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
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
    	Employee employeeShifted = employeeShiftingService.assignShiftToEmployee(idShift, nik);
    	if(employeeShifted!=null)
    		return new ResponseEntity<Employee>(employeeShifted, HttpStatus.OK);
    	else
    		return new ResponseEntity<Employee>(employeeShifted, HttpStatus.BAD_REQUEST);
    }
}