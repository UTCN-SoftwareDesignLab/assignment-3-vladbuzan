package com.assignment3.assignment3.user;

import com.assignment3.assignment3.user.model.Role;
import com.assignment3.assignment3.user.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    Page<User> findAllByRoleOrRole(Role role1, Role role2, Pageable pageable);
}
