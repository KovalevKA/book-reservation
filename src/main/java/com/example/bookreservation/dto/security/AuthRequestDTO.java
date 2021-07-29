package com.example.bookreservation.dto.security;

import lombok.Data;

@Data
public class AuthRequestDTO {

    private String username;
    private String password;
}
