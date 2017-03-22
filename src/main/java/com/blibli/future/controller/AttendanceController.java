package com.blibli.future.controller;

import com.blibli.future.service.UploadFileService;

import java.sql.Timestamp;
import java.util.Date;

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

    @Autowired
    public AttendanceController(UploadFileService uploadFileService) {
        this.uploadFileService = uploadFileService;
    }

    @PostMapping("/employees/taps/upload")
    public ResponseEntity uploadAttendanceFile(@RequestParam("file") MultipartFile file) {
        boolean fileUploaded = uploadFileService.processFile(file);

        if(fileUploaded) {
            return new ResponseEntity<Boolean>(true, HttpStatus.OK);
        }
        return new ResponseEntity<Boolean>(false, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }
    
    @PostMapping("employees/taps")
    public ResponseEntity employeeTapping(@RequestParam("type") String type, @RequestParam("tapTime") Timestamp tapTime,
    		@RequestParam("dateTap") Date dateTap, @RequestParam("nik") String nik) {

        if(1==1) {
            return new ResponseEntity<Boolean>(true, HttpStatus.OK);
        }
        return new ResponseEntity<Boolean>(false, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }
    
    @PostMapping("employees")
    public ResponseEntity Employee(@RequestParam("variabel") String type ){

        if (1==1){
            return  new ResponseEntity<Boolean>(true, HttpStatus.OK);
        }
        return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
    }
}
