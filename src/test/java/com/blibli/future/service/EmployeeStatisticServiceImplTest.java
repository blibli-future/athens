package com.blibli.future.service;

import com.blibli.future.repository.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class EmployeeStatisticServiceImplTest {
    @InjectMocks
    private EmployeeStatisticServiceImpl employeeStatisticService;
    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private EmployeeYearlyLeaveRepository employeeYearlyLeaveRepository;
    @Mock
    private EmployeeSubstitutionLeaveRightRepository employeeSubstitutionLeaveRightRepository;
    @Mock
    private AttendanceRepository attendanceRepository;
    @Mock
    private SubstitutionLeaveRightRepository substitutionLeaveRightRepository;

    @Test
    public void generateSummaries() throws Exception {

    }

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }
}