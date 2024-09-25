package com.digital.dto;

import java.util.List;

import jakarta.validation.constraints.NotNull;


public class SignupRequest {
    private String email;
    private String name;
    private String password;
    
    @NotNull(message = "Roles are required")
    private List<String> roleNames; // Use role names instead of IDs

    // Getters and Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoleNames() {
        return roleNames;
    }

    public void setRoleNames(List<String> roleNames) {
        this.roleNames = roleNames;
    }
}
