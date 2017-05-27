package com.blibli.future.service;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blibli.future.model.EmployeeShift;
import com.blibli.future.repository.EmployeeShiftRepository;
import com.blibli.future.service.api.EmployeeShiftingService;
import com.blibli.future.vo.EmployeeShiftVo;

@Service
public class EmployeeShiftingServiceImpl implements EmployeeShiftingService{
	private EmployeeShiftRepository shiftRepo;
	
	@Autowired
	public EmployeeShiftingServiceImpl(EmployeeShiftRepository shiftRepo){
		this.shiftRepo = shiftRepo;
	}
	
	public EmployeeShift processShifting(EmployeeShift employeeShift){
		if(employeeShift!=null){
			return shiftRepo.save(employeeShift);
		}
		return null;
	}
	
	public boolean processUpdateShifting(String idShiftLama, String idShift, String nik){
		if(idShift!=null && nik!=null){
			EmployeeShift employeeShift = shiftRepo.findOneByNikAndIdShift(nik, idShiftLama);
			employeeShift.setIdShift(idShift);
			shiftRepo.save(employeeShift);
			return true;
		}
		return false;
	}
	
	public List<EmployeeShift> processGetShifting(String idShift){
		List<EmployeeShift> listEmployeeShift = new ArrayList<>();
		listEmployeeShift = shiftRepo.findByIdShift(idShift);
		if(listEmployeeShift!=null){
			return listEmployeeShift;
		}
		return null;
	}
}
