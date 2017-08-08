package com.blibli.future.controller;

import com.blibli.future.exception.IdNotFoundException;
import com.blibli.future.model.Shift;
import com.blibli.future.service.api.ShiftService;
import com.blibli.future.vo.ShiftVo;
import com.blibli.future.vo.response.EmployeeShiftResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class ShiftController {

    public final String BASE_PATH = "/shifts";
    public final String PATH_WITH_ID = BASE_PATH + "/{id}";
    public final String SHIFT_EMPLOYEES_PATH = PATH_WITH_ID + "/employees";

    private ShiftService shiftService;

    @Autowired
    public ShiftController(ShiftService shiftService) {
        this.shiftService = shiftService;
    }

    @GetMapping(value = BASE_PATH)
    public ResponseEntity<List<Shift>> getAllShift() {
        return new ResponseEntity<>(shiftService.getAllShift(), HttpStatus.OK);
    }

    @PostMapping(value = BASE_PATH)
    public ResponseEntity<Shift> createNewShift(@RequestBody ShiftVo shiftVO) {
        return new ResponseEntity<>(this.shiftService.createShift(shiftVO), HttpStatus.OK);
    }

    @GetMapping(value = PATH_WITH_ID)
    public ResponseEntity getShiftById(@PathVariable String id) throws IdNotFoundException {
        return new ResponseEntity<>(this.shiftService.getShiftById(id), HttpStatus.OK);
    }

    @PutMapping(value = PATH_WITH_ID)
    public ResponseEntity updateShift(@PathVariable String id, @RequestBody ShiftVo shiftVO) throws IdNotFoundException {
        return new ResponseEntity<>(this.shiftService.updateShift(id, shiftVO), HttpStatus.OK);
    }

    @DeleteMapping(value = PATH_WITH_ID)
    public ResponseEntity deleteShift(@PathVariable String id) throws IdNotFoundException {
        this.shiftService.deleteShift(id);

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = SHIFT_EMPLOYEES_PATH)
    public ResponseEntity<Set<EmployeeShiftResponse>> getWorkingEmployees(@PathVariable String id) throws IdNotFoundException {
         return new ResponseEntity<>(this.shiftService.getWorkingEmployees(id), HttpStatus.OK);
    }
}
