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

@RestController
@RequestMapping("/api/v1/")
public class UsersController {

    @Autowired
    private JwtUtility jwtUtility;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/public/register", method = RequestMethod.POST)

    public ResponseEntity<?> register(@RequestBody Users users, HttpServletRequest httpServletRequest) {


            Users attemptingUser = usersRepository.findByEmail(users.getEmail());

            if (attemptingUser == null) {

                users.setPassword(passwordEncoder.encode(users.getPassword()));

                String token = jwtUtility.generateToken(users.getEmail());

                usersRepository.save(users);

                UserInfoDTO response = new UserInfoDTO(
                        "User registered successfully!",
                        users.getFirstName(),
                        users.getLastName(),
                        users.getEmail(),
                        token
                );

                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            } else {
                messageDTO response = new messageDTO(
                        "Email already in use!"
                );
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
    }

    @RequestMapping(value = "/public/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody Users users) {

        Users loginAttempt = usersRepository.findByEmail(users.getEmail());

        if (loginAttempt != null && passwordEncoder.matches(users.getPassword(), loginAttempt.getPassword())) {

            String token = jwtUtility.generateToken(users.getEmail());

            UserInfoDTO response = new UserInfoDTO(
                    "User logged in successfully!",
                    loginAttempt.getFirstName(),
                    loginAttempt.getLastName(),
                    loginAttempt.getEmail(),
                    token
            );


            // Log the success with proper formatting
            System.out.print("\u001B[32mUser logged in successfully: \u001B[0m"); // Green success message
            System.out.print("\u001B[32mFirst Name: \u001B[0m" + loginAttempt.getFirstName()+" "); // Green First Name
            System.out.print("\u001B[32mLast Name: \u001B[0m" + loginAttempt.getLastName()+" ");  // Green Last Name
            System.out.print("\u001B[32mEmail: \u001B[0m" + loginAttempt.getEmail()+" ");  // Green Email

            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {

            messageDTO response = new messageDTO(
                    " Email or Password mismatched"
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @RequestMapping(value = "/protected/logout",method = RequestMethod.POST)
    public ResponseEntity<?> logout(HttpServletRequest request){

        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")){

            String token = authHeader.substring(7);

            JwtSecurityFilter.blackListToken(token);

            messageDTO response = new messageDTO(
                    "user logged out successfully!"
            );
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            messageDTO response = new messageDTO(
                    "Missing or wrong Bearer token!"
            );
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
    }
}










