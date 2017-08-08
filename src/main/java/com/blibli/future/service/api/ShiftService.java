package com.blibli.future.service.api;

import com.blibli.future.exception.IdNotFoundException;
import com.blibli.future.model.Shift;
import com.blibli.future.vo.ShiftVo;
import com.blibli.future.vo.response.EmployeeShiftResponse;

import java.util.List;
import java.util.Set;

public interface ShiftService {
    List<Shift> getAllShift();

    Shift createShift(ShiftVo newShiftVO);

    Shift getShiftById(String ShiftId) throws IdNotFoundException;



    Shift updateShift(String id, ShiftVo shiftVO) throws IdNotFoundException;

    void deleteShift(String shiftId) throws IdNotFoundException;

    Set<EmployeeShiftResponse> getWorkingEmployees(String shiftId) throws IdNotFoundException;
}
