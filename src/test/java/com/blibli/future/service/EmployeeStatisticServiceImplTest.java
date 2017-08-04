package com.blibli.future.service;

import com.blibli.future.exception.IdNotFoundException;
import com.blibli.future.repository.*;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

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

    private final String NIK = "nik";

    @Test
    public void generateSummaries_fail() throws Exception {
        Mockito.when(employeeRepository.findOneByNik(NIK)).thenReturn(null);

        try {
            employeeStatisticService.generateSummaries(NIK);
        } catch (Exception e) {
            Assert.assertThat(e, Matchers.instanceOf(IdNotFoundException.class));
        }

        Mockito.verify(employeeRepository).findOneByNik(NIK);
    }

    @Test
    public void generateSummaries_success() throws Exception {
        //How to test this?
    }

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception {
        Mockito.verifyNoMoreInteractions(
                employeeRepository,
                employeeYearlyLeaveRepository,
                employeeSubstitutionLeaveRightRepository,
                attendanceRepository,
                substitutionLeaveRightRepository);

    }
}