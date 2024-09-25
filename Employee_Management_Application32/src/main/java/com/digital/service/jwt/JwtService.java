package com.digital.service.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.digital.dto.AuthResponse;
import com.digital.dto.LoginRequest;
import com.digital.entity.Customer;
import com.digital.repository.CustomerRepository;
import com.digital.utils.JwtUtil;

@Service
public class JwtService {

    @Autowired
    private AuthenticationManager authenticationManager; // For authenticating users

    @Autowired
    private CustomerRepository customerRepository; // Repository to fetch customer details

    @Autowired
    private JwtUtil jwtUtil; // Utility class for JWT operations

    // Authenticate user and return AuthResponse with JWT token and roles
    public AuthResponse authenticate(LoginRequest loginRequest) {
        try {
            // Authenticate the user
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication); // Set authentication in context

            // Retrieve customer and their roles
            Customer customer = customerRepository.findByEmail(loginRequest.getEmail()).orElse(null);
            if (customer != null) {
                // Generate JWT token
                String jwtToken = jwtUtil.generateToken(customer);
                return new AuthResponse(jwtToken, customer.getRoles()); // Return AuthResponse with token and roles
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging
            return null; // Return null in case of an exception
        }
        return null; // Return null if customer not found
    }
}
