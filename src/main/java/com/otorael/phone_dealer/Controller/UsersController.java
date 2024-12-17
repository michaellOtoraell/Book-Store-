package com.otorael.phone_dealer.Controller;

import com.otorael.phone_dealer.Model.Users;
import com.otorael.phone_dealer.Repository.UsersRepository;
import com.otorael.phone_dealer.ResponseDTO.UserInfoDTO;
import com.otorael.phone_dealer.ResponseDTO.messageDTO;
import com.otorael.phone_dealer.Security.JwtSecurityFilter;
import com.otorael.phone_dealer.Security.JwtUtility;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for handling user-related operations such as registration, login, and logout.
 * Provides endpoints for user registration, login, and logout with JWT authentication.
 */
@RestController
@RequestMapping("/api/v1/")
public class UsersController {

    /**
     * Utility class for handling JWT token generation.
     */
    @Autowired
    private JwtUtility jwtUtility;

    /**
     * Repository for interacting with the Users data in the database.
     */
    @Autowired
    private UsersRepository usersRepository;

    /**
     * Password encoder for securely encoding user passwords before saving them.
     */
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Registers a new user.
     * Endpoint: POST /public/register
     * @param users the user data received from the request body.
     * @param httpServletRequest the HTTP request.
     * @return ResponseEntity containing success or error response.
     */
    @RequestMapping(value = "/public/register", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody Users users, HttpServletRequest httpServletRequest) {

        // Check if the user already exists by email
        Users attemptingUser = usersRepository.findByEmail(users.getEmail());

        if (attemptingUser == null) {
            // If the user does not exist, encode the password and generate a JWT token
            users.setPassword(passwordEncoder.encode(users.getPassword()));

            String token = jwtUtility.generateToken(users.getEmail());

            // Save the new user to the database
            usersRepository.save(users);

            // Create a response DTO with user information and token
            UserInfoDTO response = new UserInfoDTO(
                    "User registered successfully!",
                    users.getFirstName(),
                    users.getLastName(),
                    users.getEmail(),
                    token
            );

            // Return a success response
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            // If the email is already in use, return an error message
            messageDTO response = new messageDTO(
                    "Email already in use!"
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    /**
     * Logs in an existing user.
     * Endpoint: POST /public/login
     * @param users the login credentials (email and password).
     * @return ResponseEntity containing the user info and JWT token if successful.
     */
    @RequestMapping(value = "/public/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody Users users) {

        // Find the user by email in the database
        Users loginAttempt = usersRepository.findByEmail(users.getEmail());

        if (loginAttempt != null && passwordEncoder.matches(users.getPassword(), loginAttempt.getPassword())) {
            // If the login credentials match, generate a JWT token
            String token = jwtUtility.generateToken(users.getEmail());

            // Create a response DTO with login details and token
            UserInfoDTO response = new UserInfoDTO(
                    "User logged in successfully!",
                    loginAttempt.getFirstName(),
                    loginAttempt.getLastName(),
                    loginAttempt.getEmail(),
                    token
            );

            // Log the successful login
            System.out.print("\u001B[32mUser logged in successfully: \u001B[0m"); // Green success message
            System.out.print("\u001B[32mFirst Name: \u001B[0m" + loginAttempt.getFirstName() + " "); // Green First Name
            System.out.print("\u001B[32mLast Name: \u001B[0m" + loginAttempt.getLastName() + " ");  // Green Last Name
            System.out.print("\u001B[32mEmail: \u001B[0m" + loginAttempt.getEmail() + " ");  // Green Email

            // Return success response with user info and token
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            // If credentials do not match, return an error response
            messageDTO response = new messageDTO(
                    " Email or Password mismatched"
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    /**
     * Logs out the user by invalidating the JWT token.
     * Endpoint: POST /protected/logout
     * @param request the HTTP request containing the Authorization header.
     * @return ResponseEntity with success or error message.
     */
    @RequestMapping(value = "/protected/logout", method = RequestMethod.POST)
    public ResponseEntity<?> logout(HttpServletRequest request){

        // Get the Authorization header from the request
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            // If a valid Bearer token is provided, extract the token
            String token = authHeader.substring(7);

            // Add the token to the blacklist to invalidate it
            JwtSecurityFilter.blackListToken(token);

            // Return success response
            messageDTO response = new messageDTO(
                    "user logged out successfully!"
            );
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            // If no Bearer token is provided, return an error message
            messageDTO response = new messageDTO(
                    "Missing or wrong Bearer token!"
            );
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
    }
}
