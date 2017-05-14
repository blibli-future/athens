package com.blibli.future.repository;


import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.blibli.future.model.Attendance;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AttendanceRepositoryTest {
	
	private static final String NIK = "nik";
	private static final LocalDate LOCAL_DATE =LocalDate.of(2017, 5, 14);
	private static final LocalTime LOCAL_TIME =	LocalTime.now();

	
	  @Autowired
	  private AttendanceRepository repository;
	  
	  
	  @Test
	  public void findOneByNikAndDateTest() {
		  
			    Attendance result = this.repository.findOneByNikAndDate(NIK, LOCAL_DATE);
			    assertThat(result, notNullValue());

	  }

	
	  @Before
	  public void setUp() throws Exception {
		  Attendance attendace = new Attendance();
		  attendace.setNik(NIK);
		  attendace.setDate(LOCAL_DATE);
		  attendace.setTapIn(LOCAL_TIME);
		  attendace.setTapOut(LOCAL_TIME);
		  
		this.repository.save(attendace);
	  }
	  
	  @After
	  public void tearDown() {
	    // do nothing
	  }
}
