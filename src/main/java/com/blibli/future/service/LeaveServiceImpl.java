package com.blibli.future.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blibli.future.enums.Gender;
import com.blibli.future.enums.MaritalStatus;
import com.blibli.future.enums.Religion;
import com.blibli.future.model.Leave;
import com.blibli.future.repository.LeaveRepository;
import com.blibli.future.service.api.LeaveService;

@Service
public class LeaveServiceImpl implements LeaveService{

	private LeaveRepository leaveRepository;
	
	@Autowired
	LeaveServiceImpl(LeaveRepository leaveRepository){
		this.leaveRepository = leaveRepository;
	}
	
	@Override
	public List<Leave> getLeave(Gender gender, MaritalStatus maritalStatus, Religion religion) {
		if(gender!=null && maritalStatus!=null && religion!=null)
		{
			List<Leave> leave = leaveRepository.findByGenderAndMaritalStatusAndReligion(gender.getName(), maritalStatus.getStatus(), religion.getName());
			if(leave!=null)
				return leave;
			else
				return null;
		}
		return null;
	}

}
