package com.blibli.future.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public List<Leave> getLeave(String gender, String maritalStatus, String religion) {
		if(gender!=null && maritalStatus!=null && religion!=null)
		{
			return leaveRepository.findByGenderAndMaritalStatusAndReligion(gender, maritalStatus, religion);
		}
		return null;
	}

}
