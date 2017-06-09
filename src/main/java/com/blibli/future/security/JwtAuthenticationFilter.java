package com.blibli.future.security;

import com.blibli.future.security.model.JwtAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    protected JwtAuthenticationFilter() {
        super("/**");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String headerToken = request.getHeader("Authorization");

        if(StringUtils.isEmpty(headerToken)) {
            //TODO: throw an exception
        }

        JwtAuthenticationToken authenticationToken = new JwtAuthenticationToken(headerToken);

        return this.getAuthenticationManager().authenticate(authenticationToken);
    }
}
