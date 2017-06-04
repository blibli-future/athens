package com.blibli.future.controller;

import com.blibli.future.dto.AuthenticationRequest;
import com.blibli.future.dto.response.AuthenticationResponse;
import com.blibli.future.dto.response.ErrorResponse;
import com.blibli.future.service.api.AuthenticationService;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class AuthenticationControllerTest {
    @InjectMocks
    private AuthenticationController authenticationController;
    @Mock
    private AuthenticationService authenticationService;

    private MockMvc mockMvc;
    private ObjectWriter JsonWriter = new ObjectMapper().writer();

    private final String JWT_TOKEN = "token";
    private final String USERNAME = "username";
    private final String PASSWORD = "password";
    private final String MESSAGE = "message";

    @Test
    public void authenticate_Success() throws Exception {
        Mockito.when(authenticationService.createToken(USERNAME, PASSWORD)).thenReturn(JWT_TOKEN);

        String authenticationRequestJson = JsonWriter.writeValueAsString(new AuthenticationRequest(USERNAME, PASSWORD));
        String expectedResponse = JsonWriter.writeValueAsString(new AuthenticationResponse(JWT_TOKEN));

        mockMvc.perform(
                MockMvcRequestBuilders.post(authenticationController.LOGIN_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authenticationRequestJson)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(expectedResponse));

        Mockito.verify(authenticationService).createToken(USERNAME, PASSWORD);
    }

    @Test
    public void authenticate_Fail() throws Exception {
        Mockito.when(authenticationService.createToken(USERNAME, PASSWORD)).thenThrow(new Exception(MESSAGE));

        String authenticationRequestJson = JsonWriter.writeValueAsString(new AuthenticationRequest(USERNAME, PASSWORD));
        String expectedResponse = JsonWriter.writeValueAsString(new ErrorResponse(MESSAGE));

        mockMvc.perform(
                MockMvcRequestBuilders.post(authenticationController.LOGIN_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authenticationRequestJson)
        )
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().json(expectedResponse));

        Mockito.verify(authenticationService).createToken(USERNAME, PASSWORD);
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.authenticationController).build();

    }

    @After
    public void tearDown() {
        Mockito.verifyNoMoreInteractions(this.authenticationService);
    }
}
