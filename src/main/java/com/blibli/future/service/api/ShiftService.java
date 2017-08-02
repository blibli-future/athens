package com.blibli.future.service.api;

import com.blibli.future.exception.IdNotFoundException;
import com.blibli.future.model.Shift;
import com.blibli.future.vo.ShiftVo;

import java.util.List;

public interface ShiftService {
    List<ShiftVo> getAllShift();

    Shift createShift(ShiftVo newShiftVO);

    Shift getShiftById(String ShiftId) throws IdNotFoundException;



    Shift updateShift(String id, ShiftVo shiftVO) throws IdNotFoundException;

    void deleteShift(String shiftId) throws IdNotFoundException;
}
