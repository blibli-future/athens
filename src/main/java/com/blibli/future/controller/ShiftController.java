package com.blibli.future.controller;

import com.blibli.future.exception.IdNotFoundException;
import com.blibli.future.model.Shift;
import com.blibli.future.service.api.ShiftService;
import com.blibli.future.vo.ShiftVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ShiftController {

    public final String BASE_PATH = "/shift";
    final String PATH_WITH_ID = BASE_PATH + "/{id}";

    private ShiftService shiftService;

    @Autowired
    public ShiftController(ShiftService shiftService) {
        this.shiftService = shiftService;
    }

    @GetMapping(value = BASE_PATH)
    public ResponseEntity<List<ShiftVo>> getAllShift() {
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
}
