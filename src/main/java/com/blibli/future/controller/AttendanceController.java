package com.blibli.future.controller;

import com.blibli.future.enums.Gender;
import com.blibli.future.model.Attendance;
import com.blibli.future.model.Employee;
import com.blibli.future.model.EmployeeShift;
import com.blibli.future.service.api.EmployeeShiftingService;
import com.blibli.future.service.api.EmployeeTappingService;
import com.blibli.future.service.api.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
public class AttendanceController {
    private EmployeeTappingService employeeTappingService;
    private EmployeeShiftingService employeeShiftingService;
    private EmployeeService employeeService;

    @Autowired
    public AttendanceController(EmployeeTappingService employeeTappingService, EmployeeService employeeService, EmployeeShiftingService employeeShiftingService) {
        this.employeeTappingService = employeeTappingService;
        this.employeeService = employeeService;
        this.employeeShiftingService = employeeShiftingService;

    }

    @PostMapping("/employees/taps/upload")
    public ResponseEntity uploadAttendanceFile(@RequestParam("file") MultipartFile file) {
        if(file == null) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        boolean fileUploaded = employeeTappingService.addTapMachineFile(file);
        
        if(fileUploaded) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @PostMapping("employees/taps")
    public ResponseEntity employeeTapping(@RequestParam("type") String type,
                                          @RequestParam("tapTime") LocalTime tapTime,
                                          @RequestParam("dateTap") LocalDate dateTap,
                                          @RequestParam("nik") String nik) {
    	boolean employeeTapped =
    			employeeTappingService.processTapping(type, nik, dateTap, tapTime);
        if(employeeTapped) {
            return new ResponseEntity(true, HttpStatus.OK);
        }
        return new ResponseEntity(false, HttpStatus.BAD_REQUEST);
    }
    
    @PutMapping("employees/taps")
    public ResponseEntity employeeTappingUpdate(@RequestParam("type") String type, @RequestParam("tapTime") LocalTime tapTime,
    		@RequestParam("dateTap") LocalDate dateTap, @RequestParam("nik") String nik) {
    	boolean employeeUpdated = 
    			employeeTappingService.processUpdateTapping(type, nik, dateTap, tapTime);
        if(employeeUpdated) {
            return new ResponseEntity(true, HttpStatus.OK);
        }
        return new ResponseEntity(false, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("employees/taps")
    public ResponseEntity<List<Attendance>> employeeTappingGet(@RequestParam("dateTap") LocalDate dateStart, 
    		@RequestParam("dateTap") LocalDate dateEnd) {
    	List<Attendance> employeeTapGetted = 
    			employeeTappingService.processGetTapping(dateStart, dateEnd);
        if(employeeTapGetted!=null) {
            return new ResponseEntity(employeeTapGetted, HttpStatus.OK);
        }
        return new ResponseEntity(employeeTapGetted, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("employees/shift")
    public ResponseEntity employeeShifting(@RequestParam("idShift") String idShift, @RequestParam("nik") String nik) {
    	boolean employeeShifted =
    			employeeShiftingService.processShifting(idShift, nik);
        if(employeeShifted) {
            return new ResponseEntity(true, HttpStatus.OK);
        }
        return new ResponseEntity(false, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("employees/shift")
    public ResponseEntity employeeShiftingUpdate(@RequestParam("idShift") String idShiftLama, 
    		@RequestParam("idShift") String idShift, @RequestParam("nik") String nik) {
    	boolean employeeShiftUpdated =
    			employeeShiftingService.processUpdateShifting(idShiftLama, idShift, nik);
        if(employeeShiftUpdated) {
            return new ResponseEntity(true, HttpStatus.OK);
        }
        return new ResponseEntity(false, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("employees/shift")
    public ResponseEntity<List<EmployeeShift>> employeeShiftingGet(@RequestParam("idShift") String idShift) {
    	List<EmployeeShift> employeeShiftGetted =
    			employeeShiftingService.processGetShifting(idShift);
        if(employeeShiftGetted!=null) {
            return new ResponseEntity(employeeShiftGetted, HttpStatus.OK);
        }
        return new ResponseEntity(employeeShiftGetted, HttpStatus.BAD_REQUEST);
    }
    
    @PostMapping("employees")
    public ResponseEntity Employee(@RequestParam("nik") String nik , @RequestParam("fullName") String fullName ,
                                   @RequestParam("chiefNik") String chiefNik, @RequestParam("chiefName") String chiefName,
                                   @RequestParam("chiefPosition") String chiefPosition, @RequestParam("chiefPositionText") String chiefPositionText,
                                   @RequestParam("level") String level, @RequestParam("startWorkingDate") LocalDate startWorkingDate,
                                   @RequestParam("endWorkingDate") LocalDate endWorkingDate, @RequestParam("gender") Gender gender,
                                   @RequestParam("maritalStatus") String maritalStatus, @RequestParam("organizationalUnitText") String organizationalUnitText,
                                   @RequestParam ("religion") String religion, @RequestParam("nameOfDept") String nameOfDept, @RequestParam("position") String position,@RequestParam("status") Boolean status){

        Employee emp = new Employee(nik, fullName,gender,position,level,organizationalUnitText,maritalStatus,religion,nameOfDept,chiefName,chiefNik,chiefPosition,chiefPositionText,startWorkingDate,endWorkingDate,status);

        if (employeeService.isEmployeeExist(nik)){
            employeeService.saveEmployee(emp);
            return  new ResponseEntity(true, HttpStatus.OK);
        }
        else {
            employeeService.saveEmployee(emp);
            return new ResponseEntity(true, HttpStatus.OK);
        }
    }





    
}
