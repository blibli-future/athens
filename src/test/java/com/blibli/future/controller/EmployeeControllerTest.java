package com.blibli.future.controller;

import com.blibli.future.dto.response.ErrorResponse;
import com.blibli.future.exception.IdNotFoundException;
import com.blibli.future.service.api.EmployeeService;
import com.blibli.future.service.api.EmployeeStatisticService;
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

public class EmployeeControllerTest {
    @InjectMocks
    private EmployeeController employeeController;
    @Mock
    private EmployeeService employeeService;
    @Mock
    private EmployeeStatisticService employeeStatisticService;

    private MockMvc mockMvc;
    private ObjectWriter JsonWriter = new ObjectMapper().writer();

    private String nik = "TEST";
    private SummariesVo summariesVo = new SummariesVo();

    @Test
    public void retrieveEmployeeSummaryTest_Success() throws Exception {
        Mockito.when(employeeStatisticService.generateSummaries(nik)).thenReturn(summariesVo);

        mockMvc.perform(
                MockMvcRequestBuilders.get(employeeController.SUMMARIES_PATH.replaceAll("\\{nik\\}", nik))
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(JsonWriter.writeValueAsString(summariesVo)));

        Mockito.verify(employeeStatisticService).generateSummaries(nik);
    }

    @Test
    public void retrieveEmployeeSummaryTest_fail() throws Exception {
        String errorMessage = "nik " + nik + " not found";
        Mockito.when(employeeStatisticService.generateSummaries(nik)).thenThrow(new IdNotFoundException(errorMessage));

        mockMvc.perform(
                MockMvcRequestBuilders.get(employeeController.SUMMARIES_PATH.replaceAll("\\{nik\\}", nik))
        )
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().json(JsonWriter.writeValueAsString(new ErrorResponse(errorMessage))));

        Mockito.verify(employeeStatisticService).generateSummaries(nik);
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.employeeController).build();
    }

    @After
    public void tearDown() {
        Mockito.verifyNoMoreInteractions(employeeService);
    }

}
