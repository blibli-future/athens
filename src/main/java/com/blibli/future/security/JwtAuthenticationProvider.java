package com.blibli.future.security;

import com.blibli.future.security.exception.JwtAuthenticationException;
import com.blibli.future.security.model.JwtAuthenticationToken;
import com.blibli.future.security.model.JwtUserDetail;
import com.blibli.future.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        JwtAuthenticationToken authenticationToken = (JwtAuthenticationToken) authentication;

        Claims claims = null;

        try {
            claims = JwtUtil.verify(authenticationToken.getCredentials());
        } catch (Exception e) {
            throw new JwtAuthenticationException("Invalid Token");
        }

        JwtUserDetail userDetail = new JwtUserDetail(
                (String) claims.get("nik"),
                claims.getSubject(),
                ((List<String>) claims.get("roles"))
                        .stream()
                        .map(role -> new SimpleGrantedAuthority(role))
                        .collect(Collectors.toSet())
        );


        return new JwtAuthenticationToken(userDetail);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
