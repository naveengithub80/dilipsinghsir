package com.digital.service;

import com.digital.dto.SignupRequest;
import com.digital.entity.Customer;

public interface AuthService {
    Customer createCustomer(SignupRequest signupRequest);
}
