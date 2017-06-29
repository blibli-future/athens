package com.blibli.future.controller;

import com.blibli.future.dto.AuthenticationRequest;
import com.blibli.future.dto.response.AuthenticationResponse;
import com.blibli.future.dto.response.ErrorResponse;
import com.blibli.future.service.api.LoginService;
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
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class LoginControllerTest {
    @InjectMocks
    private LoginController loginController;
    @Mock
    private LoginService loginService;

    private MockMvc mockMvc;
    private ObjectWriter JsonWriter = new ObjectMapper().writer();

    private final String JWT_TOKEN = "token";
    private final String USERNAME = "username";
    private final String PASSWORD = "password";
    private final String MESSAGE = "message";

    @Test
    public void login_Success() throws Exception {
        Mockito.when(loginService.createToken(USERNAME, PASSWORD)).thenReturn(JWT_TOKEN);

        String authenticationRequestJson = JsonWriter.writeValueAsString(new AuthenticationRequest(USERNAME, PASSWORD));
        String expectedResponse = JsonWriter.writeValueAsString(new AuthenticationResponse(JWT_TOKEN));

        mockMvc.perform(
                MockMvcRequestBuilders.post(loginController.LOGIN_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authenticationRequestJson)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(expectedResponse));

        Mockito.verify(loginService).createToken(USERNAME, PASSWORD);
    }

    @Test
    public void login_Fail() throws Exception {
        Mockito.when(loginService.createToken(USERNAME, PASSWORD)).thenThrow(new BadCredentialsException(MESSAGE));

        String authenticationRequestJson = JsonWriter.writeValueAsString(new AuthenticationRequest(USERNAME, PASSWORD));
        String expectedResponse = JsonWriter.writeValueAsString(new ErrorResponse(MESSAGE));

        mockMvc.perform(
                MockMvcRequestBuilders.post(loginController.LOGIN_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authenticationRequestJson)
        )
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().json(expectedResponse));

        Mockito.verify(loginService).createToken(USERNAME, PASSWORD);
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.loginController).build();

    }

    @After
    public void tearDown() {
        Mockito.verifyNoMoreInteractions(this.loginService);
    }
}
