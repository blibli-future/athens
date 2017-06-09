package com.blibli.future.service;


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

public class LoginServiceTest {
    @InjectMocks
    private LoginServiceImpl authenticationService;
    @Mock
    private AthensCredentialsRepository credentialsRepository;

    private final String USERNAME = "Username";
    private final String PASSWORD = "Password";
    private final String NIK = "Nik";
    private final AthensCredential CREDENTIAL = new AthensCredential(USERNAME, PASSWORD, NIK, null);

    @Test
    public void authenticateTest() throws Exception {
        Mockito.when(credentialsRepository.findByUsernameAndPassword(USERNAME, PASSWORD)).thenReturn(CREDENTIAL);

        String result = authenticationService.createNewToken(USERNAME, PASSWORD);

        String expected = JwtUtil.createTokenFor(CREDENTIAL);

        Mockito.verify(credentialsRepository).findByUsernameAndPassword(USERNAME, PASSWORD);

        Assert.assertEquals(expected, result);
    }

    @Test
    public void authenticateTest_Fail() throws Exception {
        Mockito.when(credentialsRepository.findByUsernameAndPassword(USERNAME, PASSWORD)).thenReturn(null);

        try {
            String result = authenticationService.createNewToken(USERNAME, PASSWORD);
        } catch (Exception e) {
            //TODO: Change the Exception CLass to a more meaningful one
            Assert.assertThat(e, Matchers.instanceOf(Exception.class));
        }

        Mockito.verify(credentialsRepository).findByUsernameAndPassword(USERNAME, PASSWORD);
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
