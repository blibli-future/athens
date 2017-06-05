package com.blibli.future.service;


import com.blibli.future.model.AthensCredential;
import com.blibli.future.repository.AthensCredentialsRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class AuthenticationServiceTest {
    @InjectMocks
    private AuthenticationServiceImpl authenticationService;
    @Mock
    private AthensCredentialsRepository credentialsRepository;

    private final String EMAIL = "Email";
    private final String PASSWORD = "Password";
    private final AthensCredential CREDENTIAL = new AthensCredential();

    @Test
    public void authenticateTest() throws Exception {
        Mockito.when(credentialsRepository.findByEmailAndNik(EMAIL, PASSWORD)).thenReturn(CREDENTIAL);

        String result = authenticationService.authenticate(EMAIL, PASSWORD);

        Claims claims = Jwts.claims();
        claims.put("nik", PASSWORD);

        String expected =
                Jwts.builder()
                        .setClaims(claims)
                        .signWith(SignatureAlgorithm.HS512, "ATHENS-SECRET")
                        .compact();

        Mockito.verify(credentialsRepository).findByEmailAndNik(EMAIL, PASSWORD);

        Assert.assertEquals(expected, result);
    }

    @Test
    public void authenticateTest_Fail() throws Exception {
        Mockito.when(credentialsRepository.findByEmailAndNik(EMAIL, PASSWORD)).thenReturn(null);

        try {
            String result = authenticationService.authenticate(EMAIL, PASSWORD);
        } catch (Exception e) {
            //TODO: Change the Exception CLass to a more meaningful one
            Assert.assertThat(e, Matchers.instanceOf(Exception.class));
        }

        Mockito.verify(credentialsRepository).findByEmailAndNik(EMAIL, PASSWORD);
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
