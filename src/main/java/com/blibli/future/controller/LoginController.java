package com.blibli.future.controller;

import com.blibli.future.dto.AuthenticationRequest;
import com.blibli.future.dto.response.AthensResponse;
import com.blibli.future.dto.response.AuthenticationResponse;
import com.blibli.future.service.api.LoginService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    private final LoginService loginService;

    private final Logger LOGGER = Logger.getLogger(LoginController.class);

    final String LOGIN_URL = "/login";

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping(value = LOGIN_URL)
    public ResponseEntity<AthensResponse> login(@RequestBody AuthenticationRequest authenticationRequest) throws AuthenticationException {
        String jwtToken = loginService.createToken(authenticationRequest.getNik(), authenticationRequest.getPassword());

        return new ResponseEntity<>(new AuthenticationResponse(jwtToken), HttpStatus.OK);
    }
}
