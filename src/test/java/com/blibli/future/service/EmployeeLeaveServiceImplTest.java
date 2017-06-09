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
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.blibli.future.enums.Gender;
import com.blibli.future.enums.MaritalStatus;
import com.blibli.future.enums.Religion;
import com.blibli.future.model.Employee;
import com.blibli.future.model.EmployeeLeave;
import com.blibli.future.model.Leave;
import com.blibli.future.repository.EmployeeLeaveRepository;
import com.blibli.future.repository.EmployeeRepository;
import com.blibli.future.repository.LeaveRepository;
import com.blibli.future.vo.EmployeeLeaveVo;

public class EmployeeLeaveServiceImplTest {
	@InjectMocks
	EmployeeLeaveServiceImpl employeeLeaveService;
	@Mock
	EmployeeRepository employeeRepository;
	@Mock
	EmployeeLeaveRepository employeeLeaveRepository;
	@Mock
	LeaveRepository leaveRepository;
	
	EmployeeLeaveVo employeeLeaveVo;
	Employee employee;
	Leave leave;
	EmployeeLeave employeeLeave;
	List<EmployeeLeave> listEmployeeLeave;
	
	@Test
	public void sentLeaveRequestTest() throws Exception{		
		Mockito.when(employeeRepository.findOneByNik(employeeLeaveVo.getNik())).thenReturn(employee);
		Mockito.when(leaveRepository.findOneById(employeeLeaveVo.getIdLeave())).thenReturn(leave);
		Mockito.when(employeeLeaveRepository.save(employeeLeave)).thenReturn(employeeLeave);
		
		EmployeeLeave employeeLeaveOutput = employeeLeaveService.sentLeaveRequest(employeeLeaveVo);
		
		Mockito.verify(employeeRepository).findOneByNik(employeeLeaveVo.getNik());
		Mockito.verify(leaveRepository).findOneById(employeeLeaveVo.getIdLeave());
		Mockito.verify(employeeLeaveRepository).save(employeeLeave);
		
		Assert.assertEquals(employeeLeaveOutput, employeeLeave);
	}
	
	@Test
	public void updateLeaveRequestTest() throws Exception{
		Mockito.when(employeeLeaveRepository.findOneById(employeeLeaveVo.getId())).thenReturn(employeeLeave);
		Mockito.when(leaveRepository.findOneById(employeeLeaveVo.getIdLeave())).thenReturn(leave);
		Mockito.when(employeeLeaveRepository.save(employeeLeave)).thenReturn(employeeLeave);
		
		EmployeeLeave employeeLeaveOutput = employeeLeaveService.updateLeaveRequest(employeeLeaveVo);
		
		Mockito.verify(employeeLeaveRepository).findOneById(employeeLeaveVo.getId());
		Mockito.verify(leaveRepository).findOneById(employeeLeaveVo.getIdLeave());
		Mockito.verify(employeeLeaveRepository).save(employeeLeave);
		
		Assert.assertEquals(employeeLeaveOutput, employeeLeave);
	}
	
	@Test
	public void getLeaveRequestTest() throws Exception{		
		//Get First Date Of Year
		int year = LocalDate.now().getYear();
		int month = 1;
		int dayOfMonth = 1;
		LocalDate dateStart = LocalDate.of(year, month, dayOfMonth);
		//Get End Date Of Year
		year = LocalDate.now().getYear();
		month = 12;
		dayOfMonth = 31;
		LocalDate dateEnd = LocalDate.of(year, month, dayOfMonth);
		
		Mockito.when(employeeRepository.findOneByNik(employeeLeaveVo.getNik())).thenReturn(employee);
		Mockito.when(employeeLeaveRepository.findByEmployeeAndRequestDateBetween(employee, dateStart, dateEnd)).thenReturn(listEmployeeLeave);
		
		List<EmployeeLeave> employeeLeaveListOutput = employeeLeaveService.getLeaveRequest(employeeLeaveVo.getNik());
		
		Mockito.verify(employeeRepository).findOneByNik(employeeLeaveVo.getNik());
		Mockito.verify(employeeLeaveRepository).findByEmployeeAndRequestDateBetween(employee, dateStart, dateEnd);
		
		Assert.assertEquals(employeeLeaveListOutput, listEmployeeLeave);
	}
	
	@Before
	  public void setUp() throws Exception {
	    initMocks(this);
	    this.employee = new Employee("11", "Sebastian", Gender.MALE, "IT", "1", "Develop", MaritalStatus.LAJANG, Religion.KRISTEN, "IT", "123456", "PakBossKu", "CEO", "Central", LocalDate.now(), LocalDate.now(), true);
	    this.leave = new Leave("123", "Cuti Tahun Baru", Gender.MALE, MaritalStatus.LAJANG, Religion.KRISTEN);
	    this.employeeLeaveVo = new EmployeeLeaveVo("321", "11", "123", LocalDate.now(), LocalDate.now(), "Pulang Kampoeng");
	    this.employeeLeave = new EmployeeLeave(employee, leave, LocalDate.now(), LocalDate.now(), "Pulang Kampoeng");
	    this.listEmployeeLeave = new ArrayList<>();
	  }
	
	@After
	  public void tearDown() throws Exception {
	    verifyNoMoreInteractions(this.employeeLeaveRepository);
	  }
}
