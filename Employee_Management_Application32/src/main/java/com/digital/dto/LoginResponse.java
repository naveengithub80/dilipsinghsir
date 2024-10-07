package com.digital.dto;

import java.util.List;

public record LoginResponse(String jwt, List<String> roles) {
}