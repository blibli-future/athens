package com.blibli.future.controller;

import com.blibli.future.enums.Gender;
import com.blibli.future.enums.MaritalStatus;
import com.blibli.future.enums.Religion;
import com.blibli.future.exception.UnreadableFile;
import com.blibli.future.model.Attendance;
import com.blibli.future.model.Employee;
import com.blibli.future.model.EmployeeShift;
import com.blibli.future.service.ConverterServiceImpl;
import com.blibli.future.service.EmployeeServiceImpl;
import com.blibli.future.service.EmployeeShiftingServiceImpl;
import com.blibli.future.service.EmployeeTappingServiceImpl;
import com.blibli.future.vo.EmployeeShiftVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
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

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.refEq;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class AttendanceControllerTest {
    @InjectMocks
    private AttendanceController attendanceController;
    @Mock
    private EmployeeTappingServiceImpl employeeTappingService;
    @Mock
    private EmployeeShiftingServiceImpl employeeShiftingService;
    @Mock
    private EmployeeServiceImpl employeeService;
    @Mock
    private ConverterServiceImpl converterService;
    
    EmployeeShiftVo employeeShiftVoMock;
    EmployeeShift employeeShiftMock;
    
    private MockMvc mockMvc;
    
    private ObjectWriter objectWriter = new ObjectMapper().writer();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.attendanceController).build();
        employeeShiftVoMock = new EmployeeShiftVo();
        employeeShiftMock = new EmployeeShift();
    }

    @Test
    public void uploadAttendanceFileTest_success() throws Exception {
        MediaType mediaType = new MediaType("text", "csv");
        MockMultipartFile multipartFile = new MockMultipartFile("file",  "testing".getBytes("UTF-8"));

        Mockito.when(employeeTappingService.addTapMachineFile(multipartFile))
                .thenReturn(new ArrayList<Attendance>());

        mockMvc.perform(
                MockMvcRequestBuilders.fileUpload("/employees/taps/upload")
                        .file(multipartFile)
                        .accept(mediaType)
                        .contentType(mediaType)
        ).andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(employeeTappingService).addTapMachineFile(multipartFile);
    }

    @Test
    public void uploadAttendanceFileTest_WrongFile() throws Exception {
        MediaType mediaType = new MediaType("text", "csv");
        MockMultipartFile multipartFile = new MockMultipartFile("file",  "testing".getBytes("UTF-8"));

        Mockito.when(employeeTappingService.addTapMachineFile(multipartFile))
                .thenThrow(UnreadableFile.class);

        mockMvc.perform(
                MockMvcRequestBuilders.fileUpload("/employees/taps/upload")
                        .file(multipartFile)
                        .accept(mediaType)
                        .contentType(mediaType)
        ).andExpect(MockMvcResultMatchers.status().isPreconditionFailed());

        Mockito.verify(employeeTappingService).addTapMachineFile(multipartFile);
    }

    @Test
    public void uploadAttendanceFileTest_UnableToParse() throws Exception {
        MediaType mediaType = new MediaType("text", "csv");
        MockMultipartFile multipartFile = new MockMultipartFile("file",  "testing".getBytes("UTF-8"));

        Mockito.when(employeeTappingService.addTapMachineFile(multipartFile))
                .thenThrow(DateTimeParseException.class);

        mockMvc.perform(
                MockMvcRequestBuilders.fileUpload("/employees/taps/upload")
                        .file(multipartFile)
                        .accept(mediaType)
                        .contentType(mediaType)
        ).andExpect(MockMvcResultMatchers.status().isPreconditionFailed());

        Mockito.verify(employeeTappingService).addTapMachineFile(multipartFile);
    }
    
    @Test
    public void employeeShiftingTest() throws Exception {
    	String employeeShiftVo = objectWriter.writeValueAsString(employeeShiftVoMock);
        
    	Mockito.when(converterService.map(employeeShiftVo, EmployeeShift.class)).thenReturn(employeeShiftMock);
        Mockito.when(employeeShiftingService.processShifting(employeeShiftMock)).thenReturn(employeeShiftMock);
        
        mockMvc.perform(
        		MockMvcRequestBuilders.post("/employees/shift")
                .contentType(MediaType.APPLICATION_JSON)
                .content(employeeShiftVo)
        		)
        .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(converterService).map(employeeShiftVoMock, EmployeeShift.class);
        Mockito.verify(employeeShiftingService).processShifting(employeeShiftMock);
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

    @Test
    public void employeeTest() throws Exception{
        String nik ="1234";
        String fullName="Employee Fulname";
        Gender gender= Gender.FEMALE;
        String position = "Sr. SD";
        String level = "SDE";
        String organizationalUnitText ="Commerce Engine";
        MaritalStatus maritalStatus = MaritalStatus.LAJANG;
        Religion religion = Religion.KATHOLIK;
        String nameOfDept="Technology-GDN";
        String chiefNik="9879";
        String chiefName="chief Name";
        String chiefPosition ="Development Manager";
        String chiefPositionText ="Development manager";
        Boolean status=true;
        LocalDate startWorkingDate = LocalDate.now();
        LocalDate endWorkingDate = LocalDate.now();
        
        Mockito.when(employeeService.saveEmployee(nik,fullName,gender,position,level,organizationalUnitText,maritalStatus,religion,
                nameOfDept, chiefNik,chiefName,chiefPosition,chiefPositionText, startWorkingDate,endWorkingDate,status)).thenReturn(true);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/employees").accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                        .param("nik", nik)
                        .param("fullName",fullName)
                        .param("chiefNik", chiefNik)
                        .param("chiefName", chiefName)
                        .param("chiefPosition", chiefPosition)
                        .param("chiefPositionText", chiefPositionText)
                        .param("level", level)
                        .param("startWorkingDate",startWorkingDate.toString())
                        .param("endWorkingDate",endWorkingDate.toString())
                        .param("gender",gender.toString())
                        .param("maritalStatus",maritalStatus.toString())
                        .param("organizationalUnitText",organizationalUnitText)
                        .param("religion", religion.toString())
                        .param("nameOfDept", nameOfDept)
                        .param("position",position)
                        .param("status", status.toString()))
        .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(employeeService).saveEmployee(nik,fullName,gender,position,level,organizationalUnitText,maritalStatus,religion,
                nameOfDept, chiefNik,chiefName,chiefPosition,chiefPositionText, startWorkingDate,endWorkingDate,status);
    }

    @Test
    public void employeeGetByDepartmentTests() throws Exception{
        String nameofDept = "Technology-GDN";
        List employeeList = new ArrayList<Employee>();

        Mockito.when(employeeService.getEmployeesByDept(nameofDept)).thenReturn(employeeList);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/employees").accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .param("nameOfDept",nameofDept)
        ).andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(employeeService).getEmployeesByDept(nameofDept);
    }


    @Test
    public void employeeUpdateTests() throws Exception{
    	String nik ="1234";
        String fullName="Employee Fulname";
        Gender gender= Gender.FEMALE;
        String position = "Sr. SD";
        String level = "SDE";
        String organizationalUnitText ="Commerce Engine";
        MaritalStatus maritalStatus = MaritalStatus.LAJANG;
        Religion religion =Religion.KATHOLIK;
        String nameOfDept="Technology-GDN";
        String chiefNik="9879";
        String chiefName="chief Name";
        String chiefPosition ="Development Manager";
        String chiefPositionText ="Development manager";
        Boolean status=true;
        LocalDate startWorkingDate = LocalDate.now();
        LocalDate endWorkingDate = LocalDate.now();

        Mockito.when(employeeService.updateEmployee(nik,fullName,gender,position,level,organizationalUnitText,maritalStatus,religion,
                nameOfDept, chiefNik,chiefName,chiefPosition,chiefPositionText, startWorkingDate,endWorkingDate,status)).thenReturn(true);
        
        mockMvc.perform(
                MockMvcRequestBuilders.put("/employees").accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("nik", nik)
                        .param("fullName",fullName)
                        .param("chiefNik", chiefNik)
                        .param("chiefName", chiefName)
                        .param("chiefPosition", chiefPosition)
                        .param("chiefPositionText", chiefPositionText)
                        .param("level", level)
                        .param("startWorkingDate",startWorkingDate.toString())
                        .param("endWorkingDate",endWorkingDate.toString())
                        .param("gender",gender.toString())
                        .param("maritalStatus",maritalStatus.toString())
                        .param("organizationalUnitText",organizationalUnitText)
                        .param("religion", religion.toString())
                        .param("nameOfDept", nameOfDept)
                        .param("position",position)
                        .param("status", status.toString()))
        .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(employeeService).updateEmployee(nik,fullName,gender,position,level,organizationalUnitText,maritalStatus,religion,
                nameOfDept, chiefNik,chiefName,chiefPosition,chiefPositionText, startWorkingDate,endWorkingDate,status);
    }

    @After	
    public void tearDown() throws Exception {
      verifyNoMoreInteractions(this.employeeTappingService);
      verifyNoMoreInteractions(this.employeeShiftingService);
      verifyNoMoreInteractions(this.employeeService);
    }
}
