package com.assignment3.assignment3.security;

import com.assignment3.assignment3.security.dto.SignupRequest;
import com.assignment3.assignment3.user.RoleRepository;
import com.assignment3.assignment3.user.UserRepository;
import com.assignment3.assignment3.user.model.ERole;
import com.assignment3.assignment3.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder encoder;


    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public void register(SignupRequest signUpRequest) {
        User user = User.builder()
                .username(signUpRequest.getUsername())
                .password(encoder.encode(signUpRequest.getPassword()))
                .email(signUpRequest.getEmail())
                .firstname(signUpRequest.getFirstname())
                .lastname(signUpRequest.getLastname())
                .build();
        user.setRole(roleRepository.findByName(ERole.valueOf(signUpRequest.getRole()))
                .orElseThrow(() -> new RuntimeException("Cannot find supplied role")));
        userRepository.save(user);
    }
}
