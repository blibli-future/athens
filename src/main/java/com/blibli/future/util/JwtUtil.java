package com.blibli.future.util;

import com.blibli.future.model.AthensCredential;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.stream.Collectors;


public class JwtUtil {
    private static final String JWT_SECRET_KEY = "ATHENS-SECRET";

    public static String createTokenFor(AthensCredential credential) {
        Claims claims = Jwts.claims();
        claims.setSubject(credential.getUsername());
        claims.put("nik", credential.getNik());
        claims.put("roles", credential.getRoles().stream().map(role -> role.getName()).collect(Collectors.toSet()));

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET_KEY)
                .compact();
    }

    public static Claims verify(String token) {
        return Jwts.parser().setSigningKey(JWT_SECRET_KEY).parseClaimsJws(token).getBody();
    }
}
