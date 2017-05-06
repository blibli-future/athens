package com.blibli.future.controller;

import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.time.LocalDate;

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
import org.springframework.web.bind.annotation.RequestParam;

import com.blibli.future.service.EmployeeAbsencePermitServiceImpl;
import com.blibli.future.service.EmployeeLeaveServiceImpl;
import com.blibli.future.service.LeaveServiceImpl;

public class RequestControllerTest {
	@InjectMocks
	private RequestController requestController;
	@Mock
	private EmployeeLeaveServiceImpl employeeLeaveServiceImpl;
	@Mock
	private EmployeeAbsencePermitServiceImpl employeeAbsencePermitServiceImpl;
	@Mock
	private LeaveServiceImpl leaveServiceImpl;
	
	private MockMvc mockMvc;
	private final String base = "/request/";
	private final String leave = "leave/";
	private final String leaveListing = "leave/list/";
	private final String absencepermit = "absencepermit/";
	
	@Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.requestController).build();
    }
	
	@Test
	public void sentLeaveRequestTest() throws Exception{
		String nik = "123";
		String idLeave = "321";
		LocalDate startDate = LocalDate.now();
		LocalDate endDate = LocalDate.now();
		String reason = "just a reason";
		
		Mockito.when(employeeLeaveServiceImpl.sentLeaveRequest(nik, idLeave, startDate, endDate, reason)).thenReturn(true);
        
		mockMvc.perform(
                MockMvcRequestBuilders.post(base+leave).accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .param("nik", nik)
                .param("idLeave", idLeave)
                .param("startDate", startDate.toString())
                .param("endDate", endDate.toString())
                .param("reason", reason))
		.andExpect(MockMvcResultMatchers.status().isOk());
		
		Mockito.verify(employeeLeaveServiceImpl).sentLeaveRequest(nik, idLeave, startDate, endDate, reason);

	}
	
	@Test
	public void sentAbsencePermitRequestTest() throws Exception{
		String nik = "123";
		String idAbsencePermit = "321";
		LocalDate startDate = LocalDate.now();
		LocalDate endDate = LocalDate.now();
		String reason = "just a reason";
		
		Mockito.when(employeeAbsencePermitServiceImpl.sentAbsencePermitRequest(nik, idAbsencePermit, startDate, endDate, reason)).thenReturn(true);
        
		mockMvc.perform(
                MockMvcRequestBuilders.post(base+absencepermit).accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .param("nik", nik)
                .param("idAbsencePermit", idAbsencePermit)
                .param("startDate", startDate.toString())
                .param("endDate", endDate.toString())
                .param("reason", reason))
		.andExpect(MockMvcResultMatchers.status().isOk());
		
		Mockito.verify(employeeAbsencePermitServiceImpl).sentAbsencePermitRequest(nik, idAbsencePermit, startDate, endDate, reason);

	}
	
	@After	
    public void tearDown() throws Exception {
      verifyNoMoreInteractions(this.employeeAbsencePermitServiceImpl);
      verifyNoMoreInteractions(this.employeeLeaveServiceImpl);
      verifyNoMoreInteractions(this.leaveServiceImpl);
    }
	
}
