package com.blibli.future.service;

import com.blibli.future.exception.IdNotFoundException;
import com.blibli.future.model.Shift;
import com.blibli.future.repository.ShiftRepository;
import com.blibli.future.service.api.ShiftService;
import com.blibli.future.vo.ShiftVo;
import com.blibli.future.vo.response.EmployeeShiftResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    public Shift createShift(ShiftVo newShiftVO) {
        //TODO: What is the best practice to wrap all this VO -> Model conversion in a single method
        Shift newShift = new Shift();
        newShift.setId(newShiftVO.getId());
        newShift.setName(newShiftVO.getName());
        newShift.setStartHour(LocalTime.parse(newShiftVO.getStartHour()));
        newShift.setEndHour(LocalTime.parse(newShiftVO.getEndHour()));
        newShift.setWorkDay(DayOfWeek.of(newShiftVO.getWorkDay()));
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
    public Shift updateShift(String shiftId, ShiftVo updatedShiftVO) throws IdNotFoundException {
        Shift newShift = shiftRepository.findOne(shiftId);

        if(null == newShift) {
            throw new IdNotFoundException("Shift with ID: " + shiftId + " was not found in the database");
        }

        newShift.setName(updatedShiftVO.getName());
        newShift.setStartHour(LocalTime.parse(updatedShiftVO.getStartHour()));
        newShift.setEndHour(LocalTime.parse(updatedShiftVO.getEndHour()));
        newShift.setWorkDay(DayOfWeek.of(updatedShiftVO.getWorkDay()));
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

    @Override
    public Set<EmployeeShiftResponse> getWorkingEmployees(String shiftId) throws IdNotFoundException{
        Shift shift = this.shiftRepository.findOneById(shiftId);

        if(shift == null) {
            throw new IdNotFoundException("Shift with ID: " + shiftId + " was not found in the database");
        }

        return shift.getEmployees()
                .stream()
                .map(employee -> {return new EmployeeShiftResponse(employee);})
                .collect(Collectors.toSet());
    }


}
