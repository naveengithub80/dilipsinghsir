package com.digital.dto;

import java.util.Set;

import com.digital.entity.Role;

public class AuthResponse {
    private String token;
    private Set<Role> roles;

    public AuthResponse(String token, Set<Role> roles) {
        this.token = token;
        this.roles = roles;
    }

    // Getters and Setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}