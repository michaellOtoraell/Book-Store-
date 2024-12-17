package com.otorael.phone_dealer.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
public class JwtSecurityFilter extends OncePerRequestFilter {

    @Value("${jwt.secretKey}")
    public String secretKey;

    // Set for blacklisted tokens
    private static final Set<String> blackListingToken = new HashSet<>();

    // Check if the token is blacklisted
    public boolean isTokenBlacklisted(String token){
        return blackListingToken.contains(token);
    }

    // Blacklist a token when it needs to be invalidated
    public static void blackListToken(String token){
        blackListingToken.add(token);
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        // Extract the Authorization header from the request
        String authHeader = request.getHeader("Authorization");

        // Proceed only if the header is present and starts with "Bearer "
        if (authHeader != null && authHeader.startsWith("Bearer ")){

            // Extract the token by removing "Bearer " prefix
            String token = authHeader.substring(7);

            // Check if the token is blacklisted
            if (isTokenBlacklisted(token)){
                // If blacklisted, send an unauthorized error response
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token is invalid or blacklisted");
                return;
            }

            // Try parsing and validating the token
            try {
                // Parse the claims from the token using the secret key
                Claims claim = Jwts.parser()
                        .setSigningKey(secretKey)
                        .parseClaimsJws(token)
                        .getBody();

                // Extract the email (subject) from the claims
                String email = claim.getSubject();

                // If email is valid, create the authentication token
                if (email != null){
                    // Create the UsernamePasswordAuthenticationToken with the email
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            new User(email, "", Collections.emptyList()),
                            null,
                            Collections.emptyList()
                    );

                    // Set the authentication token in the SecurityContext
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (Exception err){
                System.out.println("\u001B[32mIssue arisen: \u001B[0m"); // Red
                System.out.println("\u001B[31mInvalid token: \u001B[0m" + err.getMessage()); // Green
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token: " + err.getMessage());
                return;
            }
        }

        // Continue the filter chain if authentication is either valid or skipped
        filterChain.doFilter(request, response);
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}