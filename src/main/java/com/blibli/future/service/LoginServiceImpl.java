package com.blibli.future.service;

import com.blibli.future.enums.Role;
import com.blibli.future.model.AthensCredential;
import com.blibli.future.repository.AthensCredentialsRepository;
import com.blibli.future.service.api.LoginService;
import com.blibli.future.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class LoginServiceImpl implements LoginService {
    private final AthensCredentialsRepository credentialsRepository;

    private final String ATHENS_SECRET_KEY = "ATHENS-SECRET";

    @Autowired
    public LoginServiceImpl(AthensCredentialsRepository credentialsRepository) {
        this.credentialsRepository = credentialsRepository;
    }

    @Override
    public String createNewToken(String username, String password) throws AuthenticationException {
        AthensCredential credential = credentialsRepository.findByUsernameAndPassword(username, password);

        if(credential == null) {
            throw new BadCredentialsException("Invalid username or password");
        }

        return JwtUtil.createTokenFor(credential);
    }

    @Override
    public String createNewUser(String username, String password) {
        AthensCredential credential =
                new AthensCredential(username, password, username, Stream.of(Role.EMPLOYEE).collect(Collectors.toSet()));
        credentialsRepository.save(credential);

        return JwtUtil.createTokenFor(credential);
    }
}
