package com.digital.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.digital.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	// Custom method to find Role by its name
    Optional<Role> findByName(String name);
}
