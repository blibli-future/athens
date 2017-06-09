package com.blibli.future.util;

import com.blibli.future.enums.Role;
import com.blibli.future.model.AthensCredential;
import com.blibli.future.security.JwtUserDetail;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;


public class JwtUtil {
    private static final String JWT_SECRET_KEY = "ATHENS-SECRET";

    public static String createTokenFor(AthensCredential credential) {
        Claims claims = Jwts.claims();
        claims.setSubject(credential.getUsername());
        claims.put("nik", credential.getNik());
        claims.put("roles", credential.getRoles());

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET_KEY)
                .compact();
    }

    public static JwtUserDetail verify(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(JWT_SECRET_KEY).parseClaimsJws(token).getBody();

            return new JwtUserDetail(
                    (String) claims.get("nik"),
                    claims.getSubject(),
                    ((Set<Role>) claims.get("roles"))
                            .stream()
                            .map(role -> new SimpleGrantedAuthority(role.getName()))
                            .collect(Collectors.toSet())
            );
        } catch (Exception e) {
            return null;
        }
    }
}
