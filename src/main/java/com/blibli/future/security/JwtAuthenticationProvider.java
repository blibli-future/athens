package com.blibli.future.security;

import com.blibli.future.util.JwtUtil;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) {
        JwtAuthenticationToken unauthenticatedToken = (JwtAuthenticationToken) authentication;

        JwtUserDetail userDetail = JwtUtil.verify(unauthenticatedToken.getCredentials());

        if(userDetail == null) {
            //TODO: throw authentication exception
        }

        return new JwtAuthenticationToken(userDetail);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(JwtAuthenticationToken.class);
    }
}
