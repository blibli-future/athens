package com.blibli.future.service;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.anything;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.blibli.future.model.Employee;
import com.blibli.future.model.EmployeeLeave;
import com.blibli.future.repository.EmployeeLeaveRepository;

public class EmployeeLeaveServiceImplTest {
	@InjectMocks
	EmployeeLeaveServiceImpl employeeLeaveService;
	
	@Mock
	EmployeeLeaveRepository employeeLeaveRepository;
	
	EmployeeLeave employeeLeave;
	String id, nik, idLeave, reason;
	LocalDate startDate, endDate;
	
	@Test
	public void sentLeaveRequestTest() throws Exception{		
		this.nik = "9999";
		this.idLeave = "123";
		this.reason = "this reason";
		this.startDate = LocalDate.of(2016, 12, 30);
		this.endDate = LocalDate.of(2016, 12, 31);
		
		this.employeeLeave = new EmployeeLeave(nik, idLeave, startDate, endDate, reason);
				
		this.employeeLeaveService.sentLeaveRequest(nik, idLeave, startDate, endDate, reason);
		
		Mockito.verify(employeeLeaveRepository).save(any(EmployeeLeave.class));
	}
	
	@Test
	public void updateLeaveRequestTest() throws Exception{
		this.id = "uuu";
		this.nik = "9999";
		this.idLeave = "123";
		this.reason = "this reason";
		this.startDate = LocalDate.of(2016, 12, 30);
		this.endDate = LocalDate.of(2016, 12, 31);
		
		this.employeeLeave = new EmployeeLeave(nik, idLeave, startDate, endDate, reason);
		
		Mockito.when(employeeLeaveRepository.findById(id)).thenReturn(this.employeeLeave);

		this.employeeLeaveService.updateLeaveRequest(id, nik, idLeave, startDate, endDate, reason);
		
		Mockito.verify(employeeLeaveRepository).findById(id);
		Mockito.verify(employeeLeaveRepository).delete(any(EmployeeLeave.class));
		Mockito.verify(employeeLeaveRepository).save(any(EmployeeLeave.class));
	}
	
	@Test
	public void getLeaveRequestTest() throws Exception{		
		//Get First Date Of Year
		int year = LocalDate.now().getYear();
		int month = 1;
		int dayOfMonth = 1;
		LocalDate dateStartSearch = LocalDate.of(year, month, dayOfMonth);
		//Get End Date Of Year
		year = LocalDate.now().getYear();
		month = 12;
		dayOfMonth = 31;
		LocalDate dateEndSearch = LocalDate.of(year, month, dayOfMonth);
		
		this.nik = "9999";
		this.idLeave = "123";
		this.reason = "this reason";
		this.startDate = LocalDate.of(2017, 12, 1);
		this.endDate = LocalDate.of(2017, 12, 31);
		
		List<EmployeeLeave> listEmployeeLeave = new ArrayList<>();
		this.employeeLeave = new EmployeeLeave(nik, idLeave, startDate, endDate, reason);
		listEmployeeLeave.add(this.employeeLeave);
		
		Mockito.when(employeeLeaveRepository.findByNikAndRequestDateBetween(nik, dateStartSearch, dateEndSearch)).thenReturn(listEmployeeLeave);
		
		this.employeeLeaveService.getLeaveRequest(nik);
		
		Mockito.verify(employeeLeaveRepository).findByNikAndRequestDateBetween(nik, dateStartSearch, dateEndSearch);
	}
	
	@Before
	  public void setUp() throws Exception {
	    initMocks(this);
	  }
	
	@After
	  public void tearDown() throws Exception {
	    verifyNoMoreInteractions(this.employeeLeaveRepository);
	  }
}
