package com.digital.filters;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.digital.service.jwt.CustomerServiceImpl;
import com.digital.utils.JwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private final CustomerServiceImpl customerService;
    private final JwtUtil jwtUtil;

    @Autowired
    public JwtRequestFilter(CustomerServiceImpl customerService, JwtUtil jwtUtil) {
        this.customerService = customerService;
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        
    	
    	System.out.println("Request Is Validating Now : "+ request);
    	
    	String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
        	
        	System.out.println(" Received Header and Token Value : "+authHeader);
        	
            token = authHeader.substring(7);
            
            System.out.println(" Extracted Token From Header  : "+token);
            
            username = jwtUtil.extractUsername(token);
            
            System.out.println(" Received Header and User Name  : "+username);
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = customerService.loadUserByUsername(username);
            
            
           // System.out.println("Token recevied From Request : ");

            if (jwtUtil.validateToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }

        }

        filterChain.doFilter(request, response);

    }
}
