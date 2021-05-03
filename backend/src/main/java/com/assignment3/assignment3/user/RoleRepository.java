package com.assignment3.assignment3.user;

import com.assignment3.assignment3.user.model.ERole;
import com.assignment3.assignment3.user.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole role);
}