package com.blibli.future.service;

import com.blibli.future.model.AthensCredential;
import com.blibli.future.repository.AthensCredentialsRepository;
import com.blibli.future.service.api.LoginService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String createNewToken(String username, String password) throws Exception {
        AthensCredential credential = credentialsRepository.findByEmailAndNik(username, password);

        if(credential == null) {
            throw new Exception("Invalid username or password");
        }

        Claims claims = Jwts.claims();

        claims.put("nik", password);

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, this.ATHENS_SECRET_KEY)
                .compact();
    }
}
