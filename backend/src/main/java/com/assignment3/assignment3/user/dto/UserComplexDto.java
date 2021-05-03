package com.assignment3.assignment3.user.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserComplexDto extends UserDisplayDto{
    private String username;
    private String email;
    private String role;
}
