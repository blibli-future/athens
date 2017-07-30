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
	
	
	
	@Test
	public void findByEmployeeAndRequestDateBetweenTest() throws Exception{
		LocalDate dateStart = LocalDate.of(2015, 11, 1);
		LocalDate dateEnd = LocalDate.of(2017, 1, 31);
		
		int count = this.employeeAbsencePermitRepository.findByEmployeeAndRequestDateBetween(employee, dateStart, dateEnd).size();
		Assert.assertThat(count, Matchers.equalTo(2));
	}
	
	@Test
	public void findByIdTest() throws Exception{
		Assert.assertThat(this.target, Matchers.equalTo(this.employeeAbsencePermit1));
	}
	
	@Before
    public void setUp() throws Exception{
		this.employee = new Employee("11", "Sebastian", Gender.MALE, "IT", "1", "Develop", MaritalStatus.LAJANG, Religion.KRISTEN, "IT", "123456", LocalDate.now(), LocalDate.now(), true);
		Employee emp1 = this.employeeRepository.save(this.employee);
		
		this.employeeAbsencePermit1 = new EmployeeAbsencePermit(emp1, AbsencePermit.SICK, LocalDate.of(2016, 12, 12), LocalDate.of(2010, 12, 31), "MALES");
		EmployeeAbsencePermit eAP1 = this.employeeAbsencePermitRepository.save(this.employeeAbsencePermit1);
		this.employeeAbsencePermit2 = new EmployeeAbsencePermit(emp1, AbsencePermit.HOURLY, LocalDate.of(2016, 12, 1), LocalDate.of(2010, 12, 15), "CAPEK");
		EmployeeAbsencePermit eAP2 = this.employeeAbsencePermitRepository.save(this.employeeAbsencePermit2);
		this.employeeAbsencePermit3 = new EmployeeAbsencePermit(emp1, AbsencePermit.HOURLY, LocalDate.of(2010, 12, 12), LocalDate.of(2010, 12, 30), "LINU");
		EmployeeAbsencePermit eAP3 = this.employeeAbsencePermitRepository.save(this.employeeAbsencePermit3);
		
		this.employeeAbsencePermit1 = eAP1;
		this.employeeAbsencePermit2 = eAP2;
		this.employeeAbsencePermit3 = eAP3;
		
		this.listEmployeeAbsencePermit = new ArrayList<>();
		this.listEmployeeAbsencePermit.add(eAP1);
		this.listEmployeeAbsencePermit.add(eAP2);
		
		this.target = employeeAbsencePermitRepository.findOneById(this.employeeAbsencePermit1.getId());
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
