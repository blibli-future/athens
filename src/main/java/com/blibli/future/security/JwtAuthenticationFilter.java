package com.blibli.future.security;

import com.blibli.future.security.exception.JwtAuthenticationException;
import com.blibli.future.security.model.JwtAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@Component
public class JwtAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    private JwtAuthenticationProvider authenticationProvider;

    @Autowired
    public JwtAuthenticationFilter(JwtAuthenticationProvider authenticationProvider) {
        super("/**");
        super.setAuthenticationManager(new ProviderManager(Arrays.asList(authenticationProvider)));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String jwtToken = request.getHeader("Authorization");

        if(StringUtils.isEmpty(jwtToken)) {
            System.out.println("THROWING SOMETHING");
            throw new JwtAuthenticationException("Invalid Token");
        }
        return this.getAuthenticationManager().authenticate(new JwtAuthenticationToken(jwtToken.replaceAll("Bearer ", "")));
    }

    @Autowired
    @Override
    public void setAuthenticationSuccessHandler(AuthenticationSuccessHandler successHandler) {
        super.setAuthenticationSuccessHandler(successHandler);
    }
}
