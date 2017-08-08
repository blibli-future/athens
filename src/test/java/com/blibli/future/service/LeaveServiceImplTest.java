package com.blibli.future.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.ArrayList;
import java.util.List;

import com.blibli.future.enums.Gender;
import com.blibli.future.enums.MaritalStatus;
import com.blibli.future.enums.Religion;
import com.blibli.future.model.Leave;
import com.blibli.future.repository.LeaveRepository;

public class LeaveServiceImplTest {
	@InjectMocks
	LeaveServiceImpl leaveService;
	
	@Mock
	LeaveRepository leaveRepository;
	
	private Leave leave;
	
//	@Test
//	public void getLeaveTest() throws Exception{
//		Gender gender;
//		MaritalStatus maritalStatus; 
//		Religion religion;
//		String name, id;
//		id = "1234";
//		name = "SBG";
//		gender = Gender.MALE;
//		maritalStatus = MaritalStatus.LAJANG;
//		religion = Religion.KRISTEN;
//		
//		List<Leave> leaveList = new ArrayList<>();
//		this.leave = new Leave(id, name, gender, maritalStatus, religion);
//		leaveList.add(this.leave);
//		
//		Mockito.when(leaveRepository.findByGenderAndMaritalStatusAndReligion(gender, maritalStatus, religion)).thenReturn(leaveList);
//		
//		this.leaveService.getLeave(id);
//		
//		Mockito.verify(leaveRepository).findByGenderAndMaritalStatusAndReligion(gender, maritalStatus, religion);
//	}
	
	@Before
	  public void setUp() throws Exception {
	    initMocks(this);
	  }
	
	@After
	  public void tearDown() throws Exception {
	    verifyNoMoreInteractions(this.leaveRepository);
	  }
}
