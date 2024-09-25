package com.digital.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.digital.dto.SignupRequest;
import com.digital.entity.Customer;
import com.digital.entity.Role;
import com.digital.repository.CustomerRepository;
import com.digital.repository.RoleRepository;

@Service
public class AuthServiceImpl implements AuthService {

    private final CustomerRepository customerRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthServiceImpl(CustomerRepository customerRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Customer createCustomer(SignupRequest signupRequest) {
        // Check if customer already exists
        if (customerRepository.existsByEmail(signupRequest.getEmail())) {
            return null; // Handle existing customer scenario
        }

        Customer customer = new Customer();
        BeanUtils.copyProperties(signupRequest, customer);

        // Hash the password before saving
        String hashPassword = passwordEncoder.encode(signupRequest.getPassword());
        customer.setPassword(hashPassword);

        // Fetch roles by names
        Set<Role> roles = new HashSet<>();
        for (String roleName : signupRequest.getRoleNames()) {
            Optional<Role> optionalRole = roleRepository.findByName(roleName);
            if (optionalRole.isPresent()) {
                roles.add(optionalRole.get());
            } else {
                // Optionally, throw an exception or handle role not found
                System.out.println("Role " + roleName + " not found");
            }
        }
        customer.setRoles(roles);

        // Save the customer with roles
        return customerRepository.save(customer);
    }

}
