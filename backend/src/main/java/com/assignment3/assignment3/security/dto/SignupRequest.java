package com.assignment3.assignment3.security.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignupRequest {
    private String username;
    private String email;
    private String password;
    private String role;
    private String firstname;
    private String lastname;
}
