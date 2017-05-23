package com.blibli.future.controller;

import com.blibli.future.exception.IdNotFoundException;
import com.blibli.future.model.Shift;
import com.blibli.future.service.api.ShiftService;
import com.blibli.future.valueObject.ShiftVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ShiftController {

    public final String BASE_PATH = "/shift";
    public final String PATH_WITH_ID = BASE_PATH + "/{id}";

    private ShiftService shiftService;

    @Autowired
    public ShiftController(ShiftService shiftService) {
        this.shiftService = shiftService;
    }

    @GetMapping(value = BASE_PATH)
    public ResponseEntity<List<Shift>> getAllShift() {
        return new ResponseEntity<List<Shift>>(shiftService.getAllShift(), HttpStatus.OK);
    }

    @PostMapping(value = BASE_PATH)
    public ResponseEntity<Shift> createNewShift(@RequestBody ShiftVO shiftVO) {
        return new ResponseEntity<Shift>(this.shiftService.createShift(shiftVO), HttpStatus.OK);
    }

    @GetMapping(value = PATH_WITH_ID)
    public ResponseEntity getShiftById(@PathVariable String id) {
        try {
            return new ResponseEntity<>(this.shiftService.getShiftById(id), HttpStatus.OK);
        } catch (IdNotFoundException e) {
            //TODO: Log the exception
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = PATH_WITH_ID)
    public ResponseEntity updateShift(@PathVariable String id, @RequestBody ShiftVO shiftVO) {
        try {
            return new ResponseEntity(this.shiftService.updateShift(id, shiftVO), HttpStatus.OK);
        } catch (IdNotFoundException e) {
            //TODO: Log the exception
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = PATH_WITH_ID)
    public ResponseEntity deleteShift(@PathVariable String id) {
        try {
            this.shiftService.deleteShift(id);
        } catch (IdNotFoundException e) {
            //TODO: Log the exception
            e.printStackTrace();
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.OK);
    }
}
