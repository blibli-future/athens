package com.blibli.future.controller;

import com.blibli.future.dto.AuthenticationRequest;
import com.blibli.future.dto.response.AthensResponse;
import com.blibli.future.dto.response.AuthenticationResponse;
import com.blibli.future.dto.response.ErrorResponse;
import com.blibli.future.service.api.AuthenticationService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    private final Logger LOGGER = Logger.getLogger(AuthenticationController.class);

    public final String LOGIN_URL = "/login";

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping(value = LOGIN_URL)
    public ResponseEntity<AthensResponse> login(@RequestBody AuthenticationRequest authenticationRequest) {
        try {
            String jwtToken = authenticationService.authenticate(authenticationRequest.getNik(), authenticationRequest.getPassword());
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new AuthenticationResponse("token"), HttpStatus.OK);
    }
}
