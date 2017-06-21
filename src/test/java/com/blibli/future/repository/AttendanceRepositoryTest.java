package com.blibli.future.repository;

import java.time.LocalDate;
import java.time.LocalTime;
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

import com.blibli.future.model.Attendance;
import com.blibli.future.model.primaryKey.AttendanceKey;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AttendanceRepositoryTest {
	private final LocalTime EARLY_TIME = LocalTime.of(9, 0);
    private final LocalTime LATE_TIME = LocalTime.of(19, 0);
    private Attendance attendance1;
    private Attendance attendance2;
    private Attendance attendance3;

	
	  @Autowired
	  private AttendanceRepository attendanceRepository;
	  
	  @Test
	    public void findByAttendanceKeyTest() throws Exception {
	        AttendanceKey key = new AttendanceKey("NIK-1", LocalDate.of(2017, 3, 1));
	        Assert.assertThat(this.attendanceRepository.findByAttendanceKey(key), Matchers.equalTo(this.attendance1));
	    }

	    @Test
	    public void findByAttendanceKeyDateBetweenTest() throws Exception {
	        LocalDate firstDate = LocalDate.of(2017, 2, 1);
	        LocalDate secondDate = LocalDate.of(2017, 4, 1);
	        
	        List<Attendance> attendList = new ArrayList<>();
	        attendList.add(this.attendance1);
	        attendList.add(this.attendance3);
	        
	        Assert.assertThat(this.attendanceRepository.findByAttendanceKeyDateBetween(firstDate, secondDate), Matchers.equalTo(attendList));
	    }

	    @Before
	    public void setUp() throws Exception{
	    	this.attendance1 = new Attendance("NIK-1", LocalDate.of(2017, 3, 1), EARLY_TIME, LATE_TIME);
	    	this.attendance2 = new Attendance("NIK-1", LocalDate.of(2017, 5, 1), EARLY_TIME, LATE_TIME);
	    	this.attendance3 = new Attendance("NIK-2", LocalDate.of(2017, 3, 1), EARLY_TIME, LATE_TIME);
	        this.attendanceRepository.save(attendance1);
	        this.attendanceRepository.save(attendance2);
	        this.attendanceRepository.save(attendance3);
	    }

	    @After
	    public void tearDown() throws Exception {
	        this.attendanceRepository.deleteAll();
	    }
}
