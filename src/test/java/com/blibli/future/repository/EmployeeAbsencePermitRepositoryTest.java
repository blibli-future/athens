package com.blibli.future.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.blibli.future.enums.AbsencePermit;
import com.blibli.future.enums.Gender;
import com.blibli.future.enums.MaritalStatus;
import com.blibli.future.enums.Religion;
import com.blibli.future.model.Employee;
import com.blibli.future.model.EmployeeAbsencePermit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeAbsencePermitRepositoryTest {
	@Autowired
	EmployeeAbsencePermitRepository employeeAbsencePermitRepository;
	@Autowired
	EmployeeRepository employeeRepository;
	
	Employee employee;
	
	EmployeeAbsencePermit employeeAbsencePermit1;
	EmployeeAbsencePermit employeeAbsencePermit2;
	EmployeeAbsencePermit employeeAbsencePermit3;
	EmployeeAbsencePermit target;
	
	List<EmployeeAbsencePermit> listEmployeeAbsencePermit;
	
	LocalDate dateStart;
	LocalDate dateEnd;
	
	@Test
	public void findByEmployeeAndRequestDateBetweenTest() throws Exception{
		Assert.assertThat(this.employeeAbsencePermitRepository.findByEmployeeAndRequestDateBetween(employee, dateStart, dateEnd), Matchers.equalTo(this.listEmployeeAbsencePermit));
	}
	
	@Test
	public void findByIdTest() throws Exception{
		Assert.assertThat(this.target, Matchers.equalTo(this.employeeAbsencePermit1));
	}
	
	@Before
    public void setUp() throws Exception{
		this.employee = new Employee("11", "Sebastian", Gender.MALE, "IT", "1", "Develop", MaritalStatus.LAJANG, Religion.KRISTEN, "IT", "123456", "PakBossku", "CEO", "Central", LocalDate.now(), LocalDate.now(), true);
		Employee emp1 = this.employeeRepository.save(this.employee);
		
		this.employeeAbsencePermit1 = new EmployeeAbsencePermit(emp1, AbsencePermit.IJIN1, LocalDate.of(2016, 12, 12), LocalDate.of(2010, 12, 31), "MALES");
		EmployeeAbsencePermit eAP1 = this.employeeAbsencePermitRepository.save(this.employeeAbsencePermit1);
		this.employeeAbsencePermit2 = new EmployeeAbsencePermit(emp1, AbsencePermit.IJIN2, LocalDate.of(2016, 12, 1), LocalDate.of(2010, 12, 15), "CAPEK");
		EmployeeAbsencePermit eAP2 = this.employeeAbsencePermitRepository.save(this.employeeAbsencePermit2);
		this.employeeAbsencePermit3 = new EmployeeAbsencePermit(emp1, AbsencePermit.IJIN2, LocalDate.of(2010, 12, 12), LocalDate.of(2010, 12, 30), "LINU");
		EmployeeAbsencePermit eAP3 = this.employeeAbsencePermitRepository.save(this.employeeAbsencePermit3);
		
		this.employeeAbsencePermit1 = eAP1;
		this.employeeAbsencePermit2 = eAP2;
		this.employeeAbsencePermit3 = eAP3;
		
		this.listEmployeeAbsencePermit = new ArrayList<>();
		this.listEmployeeAbsencePermit.add(eAP1);
		this.listEmployeeAbsencePermit.add(eAP2);
		
		this.dateStart = LocalDate.of(2016, 11, 1);
		this.dateEnd = LocalDate.of(2016, 12, 31);
		
		this.target = employeeAbsencePermitRepository.findById(this.employeeAbsencePermit1.getId());
		System.out.println(this.employeeAbsencePermit1.getId());
		System.out.println(this.target.getId());
		System.out.println("BESAR DAN PANJANG!");
	}
	
	@After
    public void tearDown() throws Exception {
        this.employeeAbsencePermitRepository.deleteAll();
        this.employeeRepository.deleteAll();
    }
}
