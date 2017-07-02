package com.blibli.future.service;


import com.blibli.future.enums.Role;
import com.blibli.future.model.AthensCredential;
import com.blibli.future.repository.AthensCredentialsRepository;
import com.blibli.future.util.JwtUtil;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.BadCredentialsException;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LoginServiceTest {
    @InjectMocks
    private LoginServiceImpl authenticationService;
    @Mock
    private AthensCredentialsRepository credentialsRepository;

    private final String USERNAME = "Username";
    private final String PASSWORD = "Password";
    private final String NIK = "Nik";
    private final AthensCredential CREDENTIAL = new AthensCredential(USERNAME, PASSWORD, NIK, Stream.of(Role.ADMIN).collect(Collectors.toSet()));

    @Test
    public void authenticateTest() throws Exception {
        Mockito.when(credentialsRepository.findByNikAndPassword(USERNAME, PASSWORD)).thenReturn(CREDENTIAL);

        String result = authenticationService.createToken(USERNAME, PASSWORD);

        String expected = JwtUtil.createTokenFor(CREDENTIAL);

        Mockito.verify(credentialsRepository).findByNikAndPassword(USERNAME, PASSWORD);

        Assert.assertEquals(expected, result);
    }

    @Test
    public void authenticateTest_Fail() throws Exception {
        Mockito.when(credentialsRepository.findByNikAndPassword(USERNAME, PASSWORD)).thenReturn(null);

        try {
            authenticationService.createToken(USERNAME, PASSWORD);
        } catch (Exception e) {
            Assert.assertThat(e, Matchers.instanceOf(BadCredentialsException.class));
        }

        Mockito.verify(credentialsRepository).findByNikAndPassword(USERNAME, PASSWORD);
    }

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);


    }

    @After
    public void tearDown() {
        Mockito.verifyNoMoreInteractions(credentialsRepository);
    }
}
