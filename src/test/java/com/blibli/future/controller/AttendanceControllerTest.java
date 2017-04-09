package com.blibli.future.controller;

import com.blibli.future.service.EmployeeTappingService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class AttendanceControllerTest {
    @InjectMocks
    private AttendanceController attendanceController;
    @Mock
    private EmployeeTappingService employeeTappingService;
    private MockMvc mockMvc;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.attendanceController).build();
    }

    @Test
    public void uploadAttendanceFileTest_CsvFile() throws Exception {
        MediaType mediaType = new MediaType("text", "csv");
        MockMultipartFile multipartFile = new MockMultipartFile("file",  "testing".getBytes("UTF-8"));

        Mockito.when(employeeTappingService.addTapMachineFile(multipartFile))
                .thenReturn(true);

        mockMvc.perform(
                MockMvcRequestBuilders.fileUpload("/employees/taps/upload")
                        .file(multipartFile)
                        .accept(mediaType)
                        .contentType(mediaType)
        ).andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(employeeTappingService).addTapMachineFile(multipartFile);
    }
}
