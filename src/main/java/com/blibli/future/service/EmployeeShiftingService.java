package com.blibli.future.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blibli.future.repository.ShiftRepository;

@Service
public class EmployeeShiftingService {
	private ShiftRepository shiftRepo;
	
	@Autowired
	public EmployeeShiftingService(ShiftRepository shiftRepo){
		this.shiftRepo = shiftRepo;
	}
}
