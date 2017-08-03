package com.blibli.future.service;

import com.blibli.future.exception.IdNotFoundException;
import com.blibli.future.model.Shift;
import com.blibli.future.repository.ShiftRepository;
import com.blibli.future.service.api.ShiftService;
import com.blibli.future.vo.ShiftVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShiftServiceImpl implements ShiftService{
    private ShiftRepository shiftRepository;

    @Autowired
    public ShiftServiceImpl(ShiftRepository shiftRepository) {
        this.shiftRepository = shiftRepository;
    }

    @Override
    public List<ShiftVo> getAllShift() {
        List<Shift> allShift = shiftRepository.findAll();

        return allShift.stream().map(shift -> {
            ShiftVo  s = new ShiftVo();
            s.setId(shift.getId());
            s.setName(shift.getName());
            s.setDepartment(shift.getDepartmentEmployee());
            s.setStartHour(shift.getStartHour().toString());
            s.setEndHour(shift.getEndHour().toString());
            s.setStartDay(shift.getStartDay().getValue());
            s.setEndDay(shift.getEndDay().getValue());
            return s;
        }).collect(Collectors.toList());
    }

    @Override
    public Shift createShift(ShiftVo newShiftVO) {
        Shift newShift = new Shift();
        newShift.setId(newShiftVO.getId());
        newShift.setName(newShiftVO.getName());
        newShift.setStartHour(LocalTime.parse(newShiftVO.getStartHour()));
        newShift.setEndHour(LocalTime.parse(newShiftVO.getEndHour()));
        newShift.setStartDay(DayOfWeek.of(newShiftVO.getStartDay()));
        newShift.setEndDay(DayOfWeek.of(newShiftVO.getEndDay()));
        newShift.setDepartmentEmployee(newShiftVO.getDepartment());
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
    public Shift updateShift(String shiftId, ShiftVo updatedShiftVO) throws IdNotFoundException {
        Shift newShift = shiftRepository.findOne(shiftId);

        if(null == newShift) {
            throw new IdNotFoundException("Shift with ID: " + shiftId + " was not found in the database");
        }

        newShift.setId(updatedShiftVO.getId());
        newShift.setName(updatedShiftVO.getName());
        newShift.setStartHour(LocalTime.parse(updatedShiftVO.getStartHour()));
        newShift.setEndHour(LocalTime.parse(updatedShiftVO.getEndHour()));
        newShift.setStartDay(DayOfWeek.of(updatedShiftVO.getStartDay()));
        newShift.setEndDay(DayOfWeek.of(updatedShiftVO.getEndDay()));
        newShift.setDepartmentEmployee(updatedShiftVO.getDepartment());
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
