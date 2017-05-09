package com.blibli.future.service.api;

import com.blibli.future.exception.IdNotFoundException;
import com.blibli.future.model.Shift;
import com.blibli.future.valueObject.ShiftVO;

import java.util.List;

public interface ShiftService {
    List<Shift> getAllShift();

    Shift createShift(ShiftVO newShiftVO);

    Shift getShiftById(String ShiftId) throws IdNotFoundException;



    Shift updateShift(String id, ShiftVO shiftVO) throws IdNotFoundException;

    void deleteShift(String shiftId) throws IdNotFoundException;
}
