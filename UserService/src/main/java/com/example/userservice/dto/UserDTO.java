package com.example.userservice.dto;

import com.example.userservice.model.Role;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String username;
    private String password;

    private String email;

    private String phone;

    private Role role;
}