package com.blibli.future.service.api;

import org.springframework.security.core.AuthenticationException;

public interface LoginService {
    String createNewToken(String username, String password) throws AuthenticationException;

    String createNewUser(String username, String password);
}
