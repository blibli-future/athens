package com.blibli.future.service;

import com.blibli.future.service.api.AuthenticationService;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Override
    public String createToken(String username, String password) throws Exception {
        return null;
    }
}
