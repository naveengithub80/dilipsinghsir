package com.digital.service.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digital.entity.Role;
import com.digital.repository.RoleRepository;

import jakarta.annotation.PostConstruct;

@Service
public class RoleInitializer {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleInitializer(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void init() {
        // Create roles if they don't exist
        if (!roleRepository.findByName("ADMIN").isPresent()) {
            Role adminRole = new Role();
            adminRole.setName("ADMIN");
            roleRepository.save(adminRole);
        }

        if (!roleRepository.findByName("MANAGER").isPresent()) {
            Role managerRole = new Role();
            managerRole.setName("MANAGER");
            roleRepository.save(managerRole);
        }
    }
}
