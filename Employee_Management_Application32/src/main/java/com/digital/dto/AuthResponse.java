package com.digital.dto;

import java.util.Set;

import com.digital.entity.Role;

public class AuthResponse {
    private String jwt;
    private Set<Role> roles;

    // Constructor, getters and setters
    public AuthResponse(String jwt, Set<Role> set) {
        this.jwt = jwt;
        this.roles = set;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void getRoles(Set<Role> roles) {
        this.roles = roles;
    }
}

