package com.assignment3.assignment3.security.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtResponse {
    private String token;
    private Long id;
    private String username;
    private String email;
    private String role;
    private String firstname;
    private String lastname;
}
