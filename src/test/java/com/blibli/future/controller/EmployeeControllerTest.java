package com.blibli.future.controller;

import com.blibli.future.service.api.EmployeeService;
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

    private MockMvc mockMvc;
    private ObjectWriter JsonWriter = new ObjectMapper().writer();

    @Test
    public void retrieveEmployeeSummaryTest_Success() throws Exception {
        String nik = "TEST";
        SummariesVo summariesVo = new SummariesVo();

        Mockito.when(employeeService.generateSummaries(nik)).thenReturn(summariesVo);

        mockMvc.perform(
                MockMvcRequestBuilders.get(employeeController.SUMMARIES_PATH.replaceAll("\\{nik\\}", nik))
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(JsonWriter.writeValueAsString(summariesVo)));

        Mockito.verify(employeeService).generateSummaries(nik);
    }

    @Test
    public void retrieveEmployeeSummaryTest_fail() {
        //todo: Write fail case
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
