package com.blibli.future.controller;

import com.blibli.future.enums.Gender;
import com.blibli.future.model.Employee;
import com.blibli.future.service.EmployeeService;
import com.blibli.future.service.EmployeeShiftingService;
import com.blibli.future.service.EmployeeTappingService;
import com.blibli.future.service.UploadFileService;

import java.sql.Timestamp;
import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class AttendanceController {
    private UploadFileService uploadFileService;
    private EmployeeTappingService employeeTappingService;
    private EmployeeShiftingService employeeShiftingService;
    private EmployeeService employeeService;
    private String nik;
    private String fullname;
    private String chiefNik;
    private String chiefName;
    private String chiefPosition;
    private String chiefPositionUnitText;
    private String leveln;
    private Date startWorkingDate;
    private Date endWorkingDate;
    private Gender gender;
    private String maritalStatus;
    private String organizationalUnitText;
    private String religion;
    private String nameOfDept;


    @Autowired
    public AttendanceController(UploadFileService uploadFileService, EmployeeService employeeService) {
        this.uploadFileService = uploadFileService;
        this.employeeService = employeeService;

    }

    @PostMapping("/employees/taps/upload")
    public ResponseEntity uploadAttendanceFile(@RequestParam("file") MultipartFile file) {
        if(file == null) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        boolean fileUploaded = uploadFileService.processFile(file);

        if(fileUploaded) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @PostMapping("employees/taps")
    public ResponseEntity employeeTapping(@RequestParam("type") String type, @RequestParam("tapTime") Timestamp tapTime,
    		@RequestParam("dateTap") Date dateTap, @RequestParam("nik") String nik) {
    	boolean employeeTapped = 
    			employeeTappingService.processTapping(type, nik, tapTime, dateTap);
        if(employeeTapped) {
            return new ResponseEntity(true, HttpStatus.OK);
        }
        return new ResponseEntity(false, HttpStatus.BAD_REQUEST);
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
    
    @PostMapping("employees")
    public ResponseEntity Employee(@RequestParam("nik") String nik , @RequestParam("fullname") String fullname ,
                                   @RequestParam("chiefNik") String chiefNik, @RequestParam("chiefName") String chiefName,
                                   @RequestParam("chiefPosition") String chiefPosition, @RequestParam("chiefPositionText") String chiefPositionText,
                                   @RequestParam("level") String level, @RequestParam("startWorkingDate") Date startWorkingDate,
                                   @RequestParam("endWorkingDate") Date endWorkingDate, @RequestParam("gender") Gender gender,
                                   @RequestParam("maritalStatus") String maritalStatus, @RequestParam("organizationalUnitText") String organizationalUnitText,
                                   @RequestParam ("religion") String religion, @RequestParam("nameOfDept") String nameOfDept, @RequestParam("positon") String positon){

        Employee emp = new Employee(nik, fullname,gender,positon,level,organizationalUnitText,maritalStatus,religion,nameOfDept,chiefName,chiefNik,chiefPosition,chiefPositionText,startWorkingDate,endWorkingDate);

        if (employeeService.isEmployeeExist(nik)){
            employeeService.saveEmployee(emp);
            return  new ResponseEntity<Boolean>(true, HttpStatus.OK);
        }
        return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
    }
}
