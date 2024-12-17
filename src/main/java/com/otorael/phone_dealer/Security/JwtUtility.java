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

    /**
     * Generates a JWT token for the given email.
     * <p>
     * The token includes the email as the subject, the issuer as "michael",
     * the issue time set to the current time, and an expiration time of 10 hours
     * from the current time.
     * </p>
     *
     * @param email the email (subject) to be included in the token.
     * @return the generated JWT token as a String.
     */
    public String generateToken(String email){

        // Building the JWT token with the given email, expiration, and signing algorithm
        return Jwts.builder()
                .setIssuer("michael")
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))  // 10-hour expiration
                .signWith(SignatureAlgorithm.HS256, secretKey)  // Signing with HS256 algorithm and the secret key
                .compact();  // Generate the JWT token
    }
}
