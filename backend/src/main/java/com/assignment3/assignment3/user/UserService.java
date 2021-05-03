package com.assignment3.assignment3.user;

import com.assignment3.assignment3.user.dto.UserComplexDto;
import com.assignment3.assignment3.user.mapper.UserMapper;
import com.assignment3.assignment3.user.model.ERole;
import com.assignment3.assignment3.user.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    public List<UserComplexDto> findAllNonAdmin(int page, int usersPerPage) {
        var pageable = PageRequest.of(page, usersPerPage);
        var doctorRole = findRole(ERole.DOCTOR);
        var secretaryRole = findRole(ERole.SECRETARY);
        return userRepository.findAllByRoleOrRole(doctorRole, secretaryRole, pageable)
                .get().map(userMapper::userComplexFromUser).collect(Collectors.toList());
    }

    private Role findRole(ERole role) {
        return roleRepository.findByName(role).orElseThrow(
                ()-> new RuntimeException("Couldn't find role: " + role.name())
        );
    }
}
