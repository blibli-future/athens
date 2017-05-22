package com.blibli.future.repository;

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
	
	@Autowired
	ShiftRepository shiftRepository;

	@Before
	public void setUp() throws Exception{
		shift1 = new Shift();
	}
	
	@After
	public void tearDown() throws Exception {
		this.shiftRepository.deleteAll();
	}
}
