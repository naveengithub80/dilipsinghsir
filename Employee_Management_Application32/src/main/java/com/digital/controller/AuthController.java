package com.digital.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digital.dto.AuthResponse;
import com.digital.dto.LoginRequest;
import com.digital.dto.SignupRequest;
import com.digital.entity.Customer;
import com.digital.service.AuthService;
import com.digital.service.jwt.JwtService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthService authService;

    // Endpoint for user login
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        AuthResponse authResponse = jwtService.authenticate(loginRequest);

        if (authResponse == null) {
            return ResponseEntity.status(401).body(null);  // If login fails, return a 401 response
        }

        return ResponseEntity.ok(authResponse);  // On success, return the token and roles
    }

    // Endpoint for user registration (signup)
    @PostMapping("/signup")
    public ResponseEntity<?> registerCustomer(@RequestBody SignupRequest signupRequest) {
        Customer customer = authService.createCustomer(signupRequest);

        if (customer == null) {
            return ResponseEntity.badRequest().body("User already exists!");  // Handle duplicate users
        }

        return ResponseEntity.ok(customer);  // Return the newly created customer object
    }
}
