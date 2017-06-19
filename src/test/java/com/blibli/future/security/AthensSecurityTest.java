package com.blibli.future.security;

import com.blibli.future.controller.LoginController;
import com.blibli.future.controller.ShiftController;
import com.blibli.future.enums.Role;
import com.blibli.future.model.AthensCredential;
import com.blibli.future.service.api.LoginService;
import com.blibli.future.util.JwtUtil;
import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static io.restassured.RestAssured.given;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AthensSecurityTest {
    @Autowired
    private ShiftController shiftController;

    @Mock
    private LoginService loginService;
    @InjectMocks
    private LoginController loginController;
    @LocalServerPort
    private int serverPort;

    @Test
    public void getAllShiftTest_anon() {
        given().when().get(shiftController.BASE_PATH)
                .then()
                .statusCode(HttpStatus.SC_UNAUTHORIZED);
    }

    @Test
    public void getAllShiftTest_employee() {
        AthensCredential employeeCredential = new AthensCredential("EMPLOYEE", "PASSWORD", "EMPLOYEE-NIK", Stream.of(Role.EMPLOYEE).collect(Collectors.toSet()));
        String employeeToken = JwtUtil.createTokenFor(employeeCredential);

        given().header("Authorization", "Bearer " + employeeToken)
                .when()
                .get(shiftController.BASE_PATH)
                .then()
                .statusCode(HttpStatus.SC_FORBIDDEN);
    }

    @Test
    public void getAllShiftTest_admin() {
        AthensCredential adminCredential = new AthensCredential("ADMIN", "PASSWORD", "ADMIN-NIK", Stream.of(Role.ADMIN).collect(Collectors.toSet()));
        String adminToken = JwtUtil.createTokenFor(adminCredential);

        given().header("Authorization", "Bearer " + adminToken)
                .when()
                .get(shiftController.BASE_PATH)
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Before
    public void setUp() {
        RestAssured.port = this.serverPort;
    }
}
