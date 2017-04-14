package com.blibli.future.controller;

import com.blibli.future.service.EmployeeShiftingServiceImpl;
import com.blibli.future.service.EmployeeTappingServiceImpl;
import org.junit.After;
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

import static org.mockito.Mockito.verifyNoMoreInteractions;

public class AttendanceControllerTest {
    @InjectMocks
    private AttendanceController attendanceController;
    @Mock
    private EmployeeTappingServiceImpl employeeTappingService;
    @Mock
    private EmployeeShiftingServiceImpl employeeShiftingService;
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
    
    @Test
    public void employeeShiftingTest() throws Exception {
        String idShiftMock = "9999";
        String nikMock = "1234";

        Mockito.when(employeeShiftingService.processShifting(idShiftMock, nikMock)).thenReturn(true);
        
        mockMvc.perform(
                MockMvcRequestBuilders.post("/employees/shift").accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .param("idShift", idShiftMock)
                .param("nik", nikMock))
        .andExpect(MockMvcResultMatchers.status().isOk());


        Mockito.verify(employeeShiftingService).processShifting(idShiftMock, nikMock);
        // Question: Why asserting the Service? this method is checking the controller right?
        // -> assertTrue(employeeShiftingService.processShifting(idShiftMock, nikMock));
    }
    
    @After
    public void tearDown() throws Exception {
      verifyNoMoreInteractions(this.employeeTappingService);
      verifyNoMoreInteractions(this.employeeShiftingService);
    }
}
