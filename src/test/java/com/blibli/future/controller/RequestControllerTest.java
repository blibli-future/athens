package com.blibli.future.controller;

import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.blibli.future.enums.AbsencePermit;
import com.blibli.future.enums.Gender;
import com.blibli.future.enums.MaritalStatus;
import com.blibli.future.enums.Religion;
import com.blibli.future.model.Employee;
import com.blibli.future.model.EmployeeAbsencePermit;
import com.blibli.future.model.EmployeeLeave;
import com.blibli.future.model.Leave;
import com.blibli.future.service.LeaveServiceImpl;
import com.blibli.future.vo.EmployeeAbsencePermitVo;
import com.blibli.future.vo.EmployeeLeaveVo;

public class RequestControllerTest {
	@InjectMocks
	private RequestController requestController;
	@Mock
	private LeaveServiceImpl leaveServiceImpl;
	
	private MockMvc mockMvc;
	private ObjectWriter objectWriter = new ObjectMapper().writer();
	
	private EmployeeAbsencePermitVo employeeAbsencePermitVoMock;
	private EmployeeLeaveVo employeeLeaveVoMock;
	private EmployeeAbsencePermit employeeAbsencePermit;
	private EmployeeLeave employeeLeave;
	private List<EmployeeLeave> listEmployeeLeave;
	private List<EmployeeAbsencePermit> listEmployeeAbsencePermit;
	private Employee employee;
	private Leave leave;
	
//	@Test
//	public void sentLeaveRequestTest() throws Exception{
//		
//		Mockito.when(employeeLeaveServiceImpl.sentLeaveRequest(employeeLeaveVoMock)).thenReturn(employeeLeave);
//		
//		String employeeLeaveVo = objectWriter.writeValueAsString(employeeLeaveVoMock);
//        String jsonResult = objectWriter.writeValueAsString(employeeLeave);
//		mockMvc.perform(
//                MockMvcRequestBuilders.post(requestController.PATH_LEAVE)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(employeeLeaveVo))
//		.andExpect(MockMvcResultMatchers.status().isOk())
//		.andExpect(MockMvcResultMatchers.content().json(jsonResult));
//		
//		Mockito.verify(employeeLeaveServiceImpl).sentLeaveRequest(employeeLeaveVoMock);
//	}
//	
//	@Test
//	public void updateLeaveRequestTest() throws Exception{
//		Mockito.when(employeeLeaveServiceImpl.updateLeaveRequest(employeeLeaveVoMock)).thenReturn(employeeLeave);
//        
//		String employeeLeaveVo = objectWriter.writeValueAsString(employeeLeaveVoMock);
//        String jsonResult = objectWriter.writeValueAsString(employeeLeave);
//		
//		mockMvc.perform(
//                MockMvcRequestBuilders.put(requestController.PATH_LEAVE)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(employeeLeaveVo))
//		.andExpect(MockMvcResultMatchers.status().isOk())
//		.andExpect(MockMvcResultMatchers.content().json(jsonResult));
//		
//		Mockito.verify(employeeLeaveServiceImpl).updateLeaveRequest(employeeLeaveVoMock);
//	}
//	
//	@Test
//	public void getLeaveRequestTest() throws Exception{
//		Mockito.when(employeeLeaveServiceImpl.getLeaveRequest(employeeLeaveVoMock.getNik())).thenReturn(listEmployeeLeave);
//		
//		mockMvc.perform(
//                MockMvcRequestBuilders.get(requestController.PATH_LEAVE)
//                .accept(MediaType.APPLICATION_JSON)
//                .contentType(MediaType.APPLICATION_JSON)
//                .param("nik", employeeLeaveVoMock.getNik()))
//		.andExpect(MockMvcResultMatchers.status().isOk());
//
//		Mockito.verify(employeeLeaveServiceImpl).getLeaveRequest(employeeLeaveVoMock.getNik());
//	}
//	
//	@Test
//	public void sentAbsencePermitRequestTest() throws Exception{
//		Mockito.when(employeeAbsencePermitServiceImpl.sentAbsencePermitRequest(employeeAbsencePermitVoMock)).thenReturn(employeeAbsencePermit);
//        
//		String employeeAbsencePermitVo = objectWriter.writeValueAsString(employeeAbsencePermitVoMock);
//        String jsonResult = objectWriter.writeValueAsString(employeeAbsencePermit);
//        
//		mockMvc.perform(
//                MockMvcRequestBuilders.post(requestController.PATH_ABSENCE_PERMIT)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(employeeAbsencePermitVo))
//		.andExpect(MockMvcResultMatchers.status().isOk())
//		.andExpect(MockMvcResultMatchers.content().json(jsonResult));
//		
//		Mockito.verify(employeeAbsencePermitServiceImpl).sentAbsencePermitRequest(employeeAbsencePermitVoMock);
//	}
//	
//	@Test
//	public void updateAbsencePermitRequestTest() throws Exception{
//		Mockito.when(employeeAbsencePermitServiceImpl.updateAbsencePermitRequest(employeeAbsencePermitVoMock)).thenReturn(employeeAbsencePermit);
//        
//		String employeeAbsencePermitVo = objectWriter.writeValueAsString(employeeAbsencePermitVoMock);
//        String jsonResult = objectWriter.writeValueAsString(employeeAbsencePermit);
//        
//		mockMvc.perform(
//                MockMvcRequestBuilders.put(requestController.PATH_ABSENCE_PERMIT)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(employeeAbsencePermitVo))
//		.andExpect(MockMvcResultMatchers.status().isOk())
//		.andExpect(MockMvcResultMatchers.content().json(jsonResult));;
//		
//		Mockito.verify(employeeAbsencePermitServiceImpl).updateAbsencePermitRequest(employeeAbsencePermitVoMock);
//	}
//	
//	@Test
//	public void getAbsenceLeaveRequestTest() throws Exception{
//		Mockito.when(employeeAbsencePermitServiceImpl.getAbsencePermitRequest(employeeAbsencePermitVoMock.getNik())).thenReturn(listEmployeeAbsencePermit);
//		
//		mockMvc.perform(
//                MockMvcRequestBuilders.get(requestController.PATH_ABSENCE_PERMIT).accept(MediaType.APPLICATION_JSON)
//                .contentType(MediaType.APPLICATION_JSON)
//                .param("nik", employeeAbsencePermitVoMock.getNik()))
//		.andExpect(MockMvcResultMatchers.status().isOk());
//
//		Mockito.verify(employeeAbsencePermitServiceImpl).getAbsencePermitRequest(employeeAbsencePermitVoMock.getNik());
//	}
	
//	@Test
//	public void getLeaveTest() throws Exception{
//		String gender = "MALE";
//		String maritalStatus = "MENIKAH";
//		String religion = "KRISTEN";
//		
//		List<Leave> leave = new ArrayList<>();
//		Mockito.when(leaveServiceImpl.getLeave(Gender.valueOf(gender), MaritalStatus.valueOf(maritalStatus), Religion.valueOf(religion))).thenReturn(leave);
//		
//		mockMvc.perform(
//                MockMvcRequestBuilders.get(requestController.PATH_LISTING_LEAVE).accept(MediaType.APPLICATION_JSON)
//                .contentType(MediaType.APPLICATION_JSON)
//                .param("gender", gender)
//                .param("maritalStatus", maritalStatus)
//                .param("religion", religion))
//		.andExpect(MockMvcResultMatchers.status().isOk());
//
//		Mockito.verify(leaveServiceImpl).getLeave(Gender.valueOf(gender), MaritalStatus.valueOf(maritalStatus), Religion.valueOf(religion));
//	}
	
	@Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.requestController).build();
        
        this.employee = new Employee("11", "Sebastian", Gender.MALE, "IT", "1", "Develop", MaritalStatus.LAJANG, Religion.KRISTEN, "IT", "123456", LocalDate.now(), true);
        this.leave = new Leave("123", "Libur Tahun Baru", Gender.MALE, MaritalStatus.LAJANG, Religion.KRISTEN);
        
        this.employeeAbsencePermit = new EmployeeAbsencePermit(employee, AbsencePermit.SICK, LocalDate.of(2017, 1, 6), LocalDate.of(2017, 1, 9), "Males");
        this.employeeAbsencePermitVoMock = new EmployeeAbsencePermitVo("321", "11", AbsencePermit.SICK, "2017-01-06", "2017-01-09", "Males");
        
        this.employeeLeaveVoMock = new EmployeeLeaveVo("321", "11", "1", "2017-01-06", "2017-01-09", "Males");
        this.employeeLeave = new EmployeeLeave(employee, leave, LocalDate.of(2017, 1, 6), LocalDate.of(2017, 1, 9), "Males");
        
        this.listEmployeeLeave  = new ArrayList<>();
        this.listEmployeeLeave.add(employeeLeave);
        
        this.listEmployeeAbsencePermit = new ArrayList<>();
        this.listEmployeeAbsencePermit.add(employeeAbsencePermit);
    }
	
	@After	
    public void tearDown() throws Exception {
      verifyNoMoreInteractions(this.leaveServiceImpl);
    }
}
