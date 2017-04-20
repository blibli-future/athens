package com.blibli.future.controller;

import com.blibli.future.enums.Gender;
import com.blibli.future.exception.UnreadableFile;
import com.blibli.future.model.Attendance;
import com.blibli.future.model.Employee;
import com.blibli.future.model.EmployeeShift;
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

    @PostMapping("employees/taps")
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
    
    @PutMapping("employees/taps")
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

    @GetMapping("employees/taps")
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
    public ResponseEntity employeeShiftingUpdate(@RequestParam("idShiftLama") String idShiftLama, 
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
