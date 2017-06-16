package com.blibli.future.security;

import com.blibli.future.security.exception.JwtAuthenticationException;
import com.blibli.future.security.model.JwtAuthenticationToken;
import com.blibli.future.security.model.JwtUserDetail;
import com.blibli.future.util.JwtUtil;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        JwtAuthenticationToken authenticationToken = (JwtAuthenticationToken) authentication;

        JwtUserDetail userDetail = JwtUtil.verify(authenticationToken.getCredentials());

        if(userDetail == null) {
            throw new JwtAuthenticationException("Invalid Token");
        }

        JwtAuthenticationToken authenticatedToken = new JwtAuthenticationToken(userDetail);
        return authenticatedToken;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
