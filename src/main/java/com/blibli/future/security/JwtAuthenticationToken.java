package com.blibli.future.security;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtAuthenticationToken implements Authentication {
    private static final long serialVersionUID = -5380927358596115990L;
    private final JwtUserDetail userDetail;
    private final String jwtToken;

    public JwtAuthenticationToken(String jwtToken) {
        this.jwtToken = jwtToken;
        this.userDetail = null;
    }

    public JwtAuthenticationToken(JwtUserDetail userDetail) {
        this.userDetail = userDetail;
        this.jwtToken = null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getCredentials() {
        return this.jwtToken;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public JwtUserDetail getPrincipal() {
        return this.userDetail;
    }

    @Override
    public boolean isAuthenticated() {
        return jwtToken == null;
    }

    @Override
    public void setAuthenticated(boolean b) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return null;
    }
}
