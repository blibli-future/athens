package com.blibli.future.repository;

import java.time.DayOfWeek;
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

import com.blibli.future.model.Shift;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShiftRepositoryTest {
	
	private Shift shift1, shift2, shift3;
	private List<Shift> shiftList = new ArrayList<Shift>();
	
	@Autowired
	ShiftRepository shiftRepository;
	
	@Test
	public void findOneTest() throws Exception{
		Assert.assertThat(this.shiftRepository.findOne("1"), Matchers.equalTo(this.shift1));
	}
	
	@Test
	public void findAllTest() throws Exception{
		Assert.assertThat(this.shiftRepository.findAll(), Matchers.equalTo(this.shiftList));
	}

	@Before
	public void setUp() throws Exception{
		shift1 = new Shift("1", "NAME1", LocalTime.of(8, 00), LocalTime.of(17, 00), DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY, "IT", "Thamrin");
		shift2 = new Shift("2", "NAME2", LocalTime.of(8, 00), LocalTime.of(12, 00), DayOfWeek.SATURDAY, DayOfWeek.MONDAY, "Warehouse", "Cawang");
		shift3 = new Shift("3", "NAME3", LocalTime.of(8, 00), LocalTime.of(17, 00), DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY, "OP", "Pusat");
		this.shiftRepository.save(shift1);
		this.shiftRepository.save(shift2);
		this.shiftRepository.save(shift3);
		this.shiftList.add(shift1);
		this.shiftList.add(shift2);
		this.shiftList.add(shift3);
	}
	
	@After
	public void tearDown() throws Exception {
		this.shiftRepository.deleteAll();
	}
}
