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
import com.blibli.future.model.Leave;
import com.blibli.future.repository.LeaveRepository;

public class LeaveServiceImplTest {
	@InjectMocks
	LeaveServiceImpl leaveService;
	
	@Mock
	LeaveRepository leaveRepository;
	
	private Leave leave;
	
	@Test
	public void getLeaveTest() throws Exception{
		Gender gender;
		String maritalStatus, religion,name, id;
		id = "1234";
		name = "JOKO";
		gender = Gender.MALE;
		maritalStatus = "KAWIN";
		religion = "KRISTEN";
		
		List<Leave> leave = new ArrayList<>();
		Leave leaveFiller = new Leave(id, name, gender, maritalStatus, religion);
		leave.add(leaveFiller);
		
		Mockito.when(leaveRepository.findByGenderAndMaritalStatusAndReligion(gender.getName(), maritalStatus, religion)).thenReturn(leave);
		
		this.leaveService.getLeave(gender, maritalStatus, religion);
		
		Mockito.verify(leaveRepository).findByGenderAndMaritalStatusAndReligion(gender.getName(), maritalStatus, religion);
	}
	
	@Before
	  public void setUp() throws Exception {
	    initMocks(this);
	  }
	
	@After
	  public void tearDown() throws Exception {
	    verifyNoMoreInteractions(this.leaveRepository);
	  }
}
