package com.blibli.future.service;

import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.MockitoAnnotations.initMocks;

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

import com.blibli.future.enums.AbsencePermit;
import com.blibli.future.enums.Gender;
import com.blibli.future.enums.MaritalStatus;
import com.blibli.future.enums.Religion;
import com.blibli.future.model.Employee;
import com.blibli.future.model.EmployeeAbsencePermit;
import com.blibli.future.repository.EmployeeAbsencePermitRepository;
import com.blibli.future.repository.EmployeeRepository;
import com.blibli.future.vo.EmployeeAbsencePermitVo;

public class EmployeeAbsencePermitServiceImplTest {
	@InjectMocks
	EmployeeAbsencePermitServiceImpl employeeAbsencePermitService;
	@Mock
	EmployeeRepository employeeRepository;
	@Mock
	EmployeeAbsencePermitRepository employeeAbsencePermitRepository;
	
	EmployeeAbsencePermitVo employeeAbsencePermitVo;
	Employee employee;
	EmployeeAbsencePermit employeeAbsencePermit;
	List<EmployeeAbsencePermit> listEmployeeAbsencePermit;
	
	@Test
	public void sentAbsencePermitRequestTest() throws Exception{
		Mockito.when(employeeRepository.findOneByNik(employeeAbsencePermitVo.getNik())).thenReturn(employee);
		Mockito.when(employeeAbsencePermitRepository.save(employeeAbsencePermit)).thenReturn(employeeAbsencePermit);
		
		EmployeeAbsencePermit employeeAbsenceOutput = employeeAbsencePermitService.sentAbsencePermitRequest(employeeAbsencePermitVo);
		
		Mockito.verify(employeeRepository).findOneByNik(employeeAbsencePermitVo.getNik());
		Mockito.verify(employeeAbsencePermitRepository).save(employeeAbsencePermit);
		
        Assert.assertEquals(employeeAbsenceOutput, employeeAbsencePermit);
	}
	
	@Test
	public void getAbsencePermitRequest() throws Exception{
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
		
		Mockito.when(employeeRepository.findOneByNik(employeeAbsencePermitVo.getNik())).thenReturn(employee);
		Mockito.when(employeeAbsencePermitRepository.findByEmployeeAndRequestDateBetween(employee, dateStart, dateEnd)).thenReturn(listEmployeeAbsencePermit);
		
		List<EmployeeAbsencePermit> employeeAbsenceOutput = employeeAbsencePermitService.getAbsencePermitRequest("11");
		
		Mockito.verify(employeeRepository).findOneByNik(employeeAbsencePermitVo.getNik());
		Mockito.verify(employeeAbsencePermitRepository).findByEmployeeAndRequestDateBetween(employee, dateStart, dateEnd);
		
        Assert.assertEquals(employeeAbsenceOutput, listEmployeeAbsencePermit);
	}
	
	@Test
	public void updateAbsencePermitRequestTest() throws Exception{
		Mockito.when(employeeAbsencePermitRepository.findOneById(employeeAbsencePermitVo.getId())).thenReturn(employeeAbsencePermit);
		Mockito.when(employeeAbsencePermitRepository.save(employeeAbsencePermit)).thenReturn(employeeAbsencePermit);
		
		EmployeeAbsencePermit employeeAbsenceOutput = employeeAbsencePermitService.updateAbsencePermitRequest(employeeAbsencePermitVo);
		
		Mockito.verify(employeeAbsencePermitRepository).findOneById(employeeAbsencePermitVo.getId());
		Mockito.verify(employeeAbsencePermitRepository).save(employeeAbsencePermit);
		
        Assert.assertEquals(employeeAbsenceOutput, employeeAbsencePermit);
	}
	
	@Before
	  public void setUp() throws Exception {
	    initMocks(this);
	    this.employeeAbsencePermitVo = new EmployeeAbsencePermitVo("123", "11", AbsencePermit.SICK, "2017-01-06", "2017-01-09", "gatau");
	    this.employee = new Employee("11", "Sebastian", Gender.MALE, "IT", "1", "Develop", MaritalStatus.LAJANG, Religion.KRISTEN, "IT", "123456", LocalDate.now(), LocalDate.now(), true);;
	    this.employeeAbsencePermit = new EmployeeAbsencePermit(employee, AbsencePermit.SICK, LocalDate.now(), LocalDate.now(), "gatau");
	    this.listEmployeeAbsencePermit = new ArrayList<>();
	}
	
	@After
	  public void tearDown() throws Exception {
	    verifyNoMoreInteractions(this.employeeRepository);
	    verifyNoMoreInteractions(this.employeeAbsencePermitRepository);
	  }
}
