package com.blibli.future.security;

import com.blibli.future.controller.LoginController;
import com.blibli.future.controller.ShiftController;
import com.blibli.future.enums.Role;
import com.blibli.future.model.AthensCredential;
import com.blibli.future.service.api.LoginService;
import com.blibli.future.util.JwtUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AthensSecurityTest {
    @Autowired
    private ShiftController shiftController;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Mock
    private LoginService loginService;
    @InjectMocks
    private LoginController loginController;

    private MockMvc mockMvc;

    @Test
    public void getAllShiftTest_anon() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get(shiftController.BASE_PATH)
        ).andExpect(status().isUnauthorized());
    }

    @Test
    public void getAllShiftTest_employee() throws Exception {
        AthensCredential employeeCredential = new AthensCredential("EMPLOYEE", "PASSWORD", "EMPLOYEE-NIK", Stream.of(Role.EMPLOYEE).collect(Collectors.toSet()));
        String employeeToken = JwtUtil.createTokenFor(employeeCredential);

        mockMvc.perform(
                MockMvcRequestBuilders.get(shiftController.BASE_PATH)
                        .header("Authorization", "Bearer " + employeeToken)
        ).andExpect(status().isForbidden());
    }


    @Test
    public void getAllShiftTest_admin() throws Exception {
        AthensCredential adminCredential = new AthensCredential("ADMIN", "PASSWORD", "ADMIN-NIK", Stream.of(Role.ADMIN).collect(Collectors.toSet()));
        String adminToken = JwtUtil.createTokenFor(adminCredential);

        System.out.println(adminToken);

        mockMvc.perform(
                MockMvcRequestBuilders.get(shiftController.BASE_PATH)
                        .header("Authorization", "Bearer " + adminToken)
        ).andExpect(status().isOk());
    }

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
    }
}
