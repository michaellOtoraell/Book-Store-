package com.otorael.phone_dealer.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Injecting JwtSecurityFilter which handles JWT token validation
    private final JwtSecurityFilter jwtSecurityFilter;

    public SecurityConfig(JwtSecurityFilter jwtSecurityFilter) {
        this.jwtSecurityFilter = jwtSecurityFilter;
    }

    /**
     * Configures the HttpSecurity object to enforce JWT authentication
     * and apply appropriate authorization rules to requests.
     *
     * @param http HttpSecurity object
     * @return SecurityFilterChain which contains configured security rules
     * @throws Exception when the security configuration fails
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Disable CSRF protection since we rely on JWTs, which are stateless
                .csrf(AbstractHttpConfigurer::disable)

                // Configure session management to be stateless
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // Configure authorization rules for different request paths
                .authorizeHttpRequests(auths -> auths
                        // Protect "/api/v1/protected/**" endpoints, requiring authentication
                        .requestMatchers("/api/v1/protected/**").authenticated()

                        // Allow public access to "/api/v1/public/**" endpoints
                        .requestMatchers("/api/v1/public/**").permitAll()

                        // Default to authenticated for all other paths
                        .anyRequest().authenticated()
                )
                // Add JWT authentication filter before the UsernamePasswordAuthenticationFilter
                .addFilterBefore(jwtSecurityFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
