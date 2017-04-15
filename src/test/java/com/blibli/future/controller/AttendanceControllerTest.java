package com.blibli.future.controller;

import com.blibli.future.model.Attendance;
import com.blibli.future.model.EmployeeShift;
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

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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
    }
    
    @Test
    public void employeeShiftingUpdateTest() throws Exception {
    	String idShiftLama = "9989";
    	String idShift = "9999";
        String nik = "1234";

        Mockito.when(employeeShiftingService.processUpdateShifting(idShiftLama, idShift, nik)).thenReturn(true);
        
        mockMvc.perform(
                MockMvcRequestBuilders.put("/employees/shift").accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .param("idShiftLama", idShiftLama)
                .param("idShift", idShift)
                .param("nik", nik))
        .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(employeeShiftingService).processUpdateShifting(idShiftLama, idShift, nik);
    }
    
    @Test
    public void employeeShiftingGetTest() throws Exception {
    	String idShift = "9999";
    	List employeeShiftList = new ArrayList<EmployeeShift>();
        Mockito.when(employeeShiftingService.processGetShifting(idShift)).thenReturn(employeeShiftList);
        
        mockMvc.perform(
                MockMvcRequestBuilders.get("/employees/shift").accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .param("idShift", idShift))
        .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(employeeShiftingService).processGetShifting(idShift);
    }
    
    @Test
    public void employeeTappingTest() throws Exception {
        String type = "in";
        String nik = "1234";
        LocalDate dateTap = LocalDate.now();
        LocalTime tapTime = LocalTime.now();

        Mockito.when(employeeTappingService.processTapping(type, nik, dateTap, tapTime)).thenReturn(true);
        
        mockMvc.perform(
                MockMvcRequestBuilders.post("/employees/taps").accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .param("type", type)
                .param("nik", nik)
                .param("dateTap", dateTap.toString())
                .param("tapTime", tapTime.toString()))
        .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(employeeTappingService).processTapping(type, nik, dateTap, tapTime);
    }
    
    @Test
    public void employeeTappingUpdateTest() throws Exception {
        String type = "in";
        String nik = "1234";
        LocalDate dateTap = LocalDate.now();
        LocalTime tapTime = LocalTime.now();

        Mockito.when(employeeTappingService.processUpdateTapping(type, nik, dateTap, tapTime)).thenReturn(true);
        
        mockMvc.perform(
                MockMvcRequestBuilders.put("/employees/taps").accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .param("type", type)
                .param("nik", nik)
                .param("dateTap", dateTap.toString())
                .param("tapTime", tapTime.toString()))
        .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(employeeTappingService).processUpdateTapping(type, nik, dateTap, tapTime);
    }
    
    @Test
    public void employeeTappingGetTest() throws Exception {
        LocalDate dateStart = LocalDate.of(2016, 11, 13);
        LocalDate dateEnd = LocalDate.now();

        List attendance = new ArrayList<Attendance>();
        Mockito.when(employeeTappingService.processGetTapping(dateStart, dateEnd)).thenReturn(attendance);
        
        mockMvc.perform(
                MockMvcRequestBuilders.get("/employees/taps").accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .param("dateStart", dateStart.toString())
                .param("dateEnd", dateEnd.toString()))
        .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(employeeTappingService).processGetTapping(dateStart, dateEnd);
    }
    
    @After
    public void tearDown() throws Exception {
      verifyNoMoreInteractions(this.employeeTappingService);
      verifyNoMoreInteractions(this.employeeShiftingService);
    }
}
