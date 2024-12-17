package com.otorael.phone_dealer.Security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtility {

    @Value("${jwt.secretKey}")
    private String secretKey;

    public String generateToken(String email){

        return Jwts.builder()
                .setIssuer("michael")
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+ 1000 * 60 * 60 *10 ))
                .signWith(SignatureAlgorithm.HS256,secretKey)
                .compact();
    }
}
