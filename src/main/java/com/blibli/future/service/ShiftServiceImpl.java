package com.blibli.future.service;

import com.blibli.future.exception.IdNotFoundException;
import com.blibli.future.model.Shift;
import com.blibli.future.repository.ShiftRepository;
import com.blibli.future.service.api.ShiftService;
import com.blibli.future.valueObject.ShiftVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShiftServiceImpl implements ShiftService{
    private ShiftRepository shiftRepository;

    @Autowired
    public ShiftServiceImpl(ShiftRepository shiftRepository) {
        this.shiftRepository = shiftRepository;
    }

    @Override
    public List<Shift> getAllShift() {
        return shiftRepository.findAll();
    }

    @Override
    public Shift createShift(ShiftVO newShiftVO) {
        //TODO: What is the best practice to wrap all this VO -> Model conversion in a single method
        Shift newShift = new Shift();
        newShift.setId(newShiftVO.getId());
        newShift.setName(newShiftVO.getName());
        newShift.setStartHour(newShiftVO.getStartHour());
        newShift.setEndHour(newShiftVO.getEndHour());
        newShift.setStartDay(newShiftVO.getStartDay());
        newShift.setEndDay(newShiftVO.getEndDay());
        newShift.setDepartmentEmployee(newShiftVO.getDepartmentEmployee());
        newShift.setLocation(newShiftVO.getLocation());

        return shiftRepository.save(newShift);
    }

    @Override
    public Shift getShiftById(String shiftId) throws IdNotFoundException {
        Shift searchedShift = shiftRepository.findOne(shiftId);

        if(null == searchedShift) {
            throw new IdNotFoundException("Shift with ID: " + shiftId + " was not found in the database");
        }
        return searchedShift;
    }

    @Override
    public Shift updateShift(String shiftId, ShiftVO updatedShiftVO) throws IdNotFoundException {
        Shift newShift = shiftRepository.findOne(shiftId);

        if(null == newShift) {
            throw new IdNotFoundException("Shift with ID: " + shiftId + " was not found in the database");
        }

        newShift.setName(updatedShiftVO.getName());
        newShift.setStartHour(updatedShiftVO.getStartHour());
        newShift.setEndHour(updatedShiftVO.getEndHour());
        newShift.setStartDay(updatedShiftVO.getStartDay());
        newShift.setEndDay(updatedShiftVO.getEndDay());
        newShift.setDepartmentEmployee(updatedShiftVO.getDepartmentEmployee());
        newShift.setLocation(updatedShiftVO.getLocation());

        return shiftRepository.save(newShift);
    }

    @Override
    public void deleteShift(String shiftId) throws IdNotFoundException {
        if(!shiftRepository.exists(shiftId)) {
            throw new IdNotFoundException("Shift with ID: " + shiftId + " was not found in the database");
        }

        shiftRepository.delete(shiftId);
    }
}
