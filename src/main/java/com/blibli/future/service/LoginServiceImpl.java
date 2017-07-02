package com.blibli.future.service;

import com.blibli.future.model.AthensCredential;
import com.blibli.future.repository.AthensCredentialsRepository;
import com.blibli.future.service.api.LoginService;
import com.blibli.future.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    private final AthensCredentialsRepository credentialsRepository;

    private final String ATHENS_SECRET_KEY = "ATHENS-SECRET";

    @Autowired
    public LoginServiceImpl(AthensCredentialsRepository credentialsRepository) {
        this.credentialsRepository = credentialsRepository;
    }

    @Override
    public String createToken(String username, String password) throws AuthenticationException {
        AthensCredential credential = credentialsRepository.findByNikAndPassword(username, password);

        if(credential == null) {
            throw new BadCredentialsException("Invalid username or password");
        }

        return JwtUtil.createTokenFor(credential);
    }
}
