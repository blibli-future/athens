package com.blibli.future.service;


import com.blibli.future.exception.IdNotFoundException;
import com.blibli.future.model.Employee;
import com.blibli.future.model.Shift;
import com.blibli.future.repository.EmployeeRepository;
import com.blibli.future.vo.ShiftVo;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.MockitoAnnotations.initMocks;

public class EmployeeServiceImplTest {
    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private final String NIK = "NIK";

    @Test
    public void getAssignedShiftTest_success() throws Exception {
        Shift shift1 = new Shift("1", "shift-1", LocalTime.NOON, LocalTime.MIDNIGHT, DayOfWeek.MONDAY, "dept-1", "location-A");
        Shift shift2 = new Shift("2", "shift-2", LocalTime.NOON, LocalTime.MIDNIGHT, DayOfWeek.TUESDAY, "dept-1", "location-A");
        Set<Shift> employeeShift =
                new HashSet<>(Arrays.asList(shift1, shift2));

        Set<ShiftVo> employeeShiftVo = employeeShift.stream()
                .map(shift -> new ShiftVo(shift))
                .collect(Collectors.toSet());

        Employee employee = new Employee();
        employee.setShifts(employeeShift);

        Mockito.when(employeeRepository.findOneByNik(NIK)).thenReturn(employee);

        //Set<ShiftVo> actual = employeeService.getAssignedShifts(NIK);

        //Assert.assertEquals(employeeShiftVo, actual);

        Mockito.verify(employeeRepository).findOneByNik(NIK);
    }

    @Test
    public void getAssignedShiftTest_fail() throws Exception {
        Mockito.when(employeeRepository.findOneByNik(NIK)).thenReturn(null);

        try {
            //employeeService.getAssignedShifts(NIK);
        } catch (Exception e) {
            Assert.assertThat(e, Matchers.instanceOf(IdNotFoundException.class));
        }

        Mockito.verify(employeeRepository).findOneByNik(NIK);
    }

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @After
    public void tearDown() throws Exception {
        verifyNoMoreInteractions(this.employeeRepository);
    }
}
