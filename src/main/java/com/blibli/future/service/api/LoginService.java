package com.blibli.future.service.api;

import org.springframework.security.core.AuthenticationException;

public interface LoginService {
    String createToken(String username, String password) throws AuthenticationException;
}
