package com.assignment3.assignment3;


import com.assignment3.assignment3.security.AuthService;
import com.assignment3.assignment3.security.dto.SignupRequest;
import com.assignment3.assignment3.user.RoleRepository;
import com.assignment3.assignment3.user.UserRepository;
import com.assignment3.assignment3.user.model.ERole;
import com.assignment3.assignment3.user.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;



@Component
@RequiredArgsConstructor
public class Bootstrapper implements ApplicationListener<ApplicationReadyEvent> {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final AuthService authService;

    @Value("${app.bootstrap}")
    private Boolean bootstrap;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if (bootstrap) {

            userRepository.deleteAll();
            roleRepository.deleteAll();
            for (ERole value : ERole.values()) {
                roleRepository.save(
                        Role.builder()
                                .name(value)
                                .build()
                );
            }
            authService.register(
                    SignupRequest.builder()
                            .username("admin")
                            .email("admin@admin.com")
                            .password("adminPassword")
                            .firstname("admin")
                            .lastname("admin")
                            .role("ADMIN")
                            .build()
            );

        }
    }
}
