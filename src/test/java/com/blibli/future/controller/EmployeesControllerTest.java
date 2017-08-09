package com.blibli.future.controller;

import com.blibli.future.dto.response.ErrorResponse;
import com.blibli.future.exception.IdNotFoundException;
import com.blibli.future.service.api.EmployeeService;
import com.blibli.future.service.api.EmployeeStatisticService;
import com.blibli.future.vo.ShiftVo;
import com.blibli.future.vo.SummariesVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class EmployeesControllerTest {
    @InjectMocks
    private EmployeesController employeeController;
    @Mock
    private EmployeeService employeeService;
    @Mock
    private EmployeeStatisticService employeeStatisticService;

    private MockMvc mockMvc;
    private ObjectWriter JsonWriter = new ObjectMapper().writer();

    private final String NIK = "TEST";
    private SummariesVo summariesVo = new SummariesVo();

    @Test
    public void retrieveEmployeeSummaryTest_Success() throws Exception {
        Mockito.when(employeeStatisticService.generateSummaries(NIK)).thenReturn(summariesVo);

        mockMvc.perform(
                MockMvcRequestBuilders.get(employeeController.SUMMARIES_PATH.replaceAll("\\{nik\\}", NIK))
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(JsonWriter.writeValueAsString(summariesVo)));

        Mockito.verify(employeeStatisticService).generateSummaries(NIK);
    }

    @Test
    public void retrieveEmployeeSummaryTest_fail() throws Exception {
        String errorMessage = "NIK " + NIK + " not found";
        Mockito.when(employeeStatisticService.generateSummaries(NIK)).thenThrow(new IdNotFoundException(errorMessage));

        mockMvc.perform(
                MockMvcRequestBuilders.get(employeeController.SUMMARIES_PATH.replaceAll("\\{nik\\}", NIK))
        )
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().json(JsonWriter.writeValueAsString(new ErrorResponse(errorMessage))));

        Mockito.verify(employeeStatisticService).generateSummaries(NIK);
    }

    @Test
    public void getAssignedShift_success() throws Exception {
        ShiftVo shiftVo1 = new ShiftVo();
        Set<ShiftVo> returnedShift = new HashSet<>(Arrays.asList(shiftVo1));

        //Mockito.when(employeeService.getAssignedShifts(NIK)).thenReturn(returnedShift);

        mockMvc.perform(
                MockMvcRequestBuilders.get(employeeController.EMPLOYEE_SHIFT_PATH.replaceAll("\\{nik\\}", NIK))
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(JsonWriter.writeValueAsString(returnedShift)));

        //Mockito.verify(employeeService).getAssignedShifts(NIK);
    }

    @Test
    public void getAssignedShift_fail() throws Exception {
        String errorMessage = "NIK " + NIK + " not found";
        //Mockito.when(employeeService.getAssignedShifts(NIK)).thenThrow(new IdNotFoundException(errorMessage));

        mockMvc.perform(
                MockMvcRequestBuilders.get(employeeController.EMPLOYEE_SHIFT_PATH.replaceAll("\\{nik\\}", NIK))
        )
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().json(JsonWriter.writeValueAsString(new ErrorResponse(errorMessage))));

        //Mockito.verify(employeeService).getAssignedShifts(NIK);
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(this.employeeController)
                .setControllerAdvice(new AthensControllerAdvice())
                .build();
    }

    @After
    public void tearDown() {
        Mockito.verifyNoMoreInteractions(employeeService);
    }

}
