package com.digital.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.digital.filters.JwtRequestFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfiguration {

    private final JwtRequestFilter jwtRequestFilter;

    @Autowired
    public WebSecurityConfiguration(JwtRequestFilter jwtRequestFilter) {
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .cors().and().csrf().disable()  // Enable CORS and disable CSRF
            .authorizeRequests()
            .requestMatchers("/signup", "/login").permitAll()  // Allow signup and login
            .requestMatchers("/api/v1/employees").hasRole("ADMIN")  // Restrict employees API to admin
            .anyRequest().authenticated()  // All other requests must be authenticated
            .and()
            .exceptionHandling().accessDeniedPage("/403")  // Custom access denied page
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)  // Stateless session
            .and()
            .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)  // JWT filter
            .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
