package com.blibli.future.controller;

import com.blibli.future.dto.AuthenticationRequest;
import com.blibli.future.dto.response.AthensResponse;
import com.blibli.future.dto.response.AuthenticationResponse;
import com.blibli.future.dto.response.ErrorResponse;
import com.blibli.future.service.api.LoginService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    private final LoginService loginService;

    private final Logger LOGGER = Logger.getLogger(LoginController.class);

    public final String LOGIN_URL = "/login";

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping(value = LOGIN_URL)
    public ResponseEntity<AthensResponse> login(@RequestBody AuthenticationRequest authenticationRequest) {
        String jwtToken;
        try {
            jwtToken = loginService.createNewToken(authenticationRequest.getNik(), authenticationRequest.getPassword());
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new AuthenticationResponse(jwtToken), HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<AthensResponse> signupDummy(@RequestBody AuthenticationRequest request) {
        String signupToken = loginService.createNewUser(request.getNik(), request.getPassword());
        return new ResponseEntity<>(new AuthenticationResponse(signupToken), HttpStatus.OK);
    }
}
