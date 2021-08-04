package com.example.bookreservation.controller;

import com.example.bookreservation.dto.security.AuthRequestDTO;
import com.example.bookreservation.service.security.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    private UserService userService;

    @Autowired
    public AuthenticationController(
        UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Login", description = "Authorization for getting token")
    @PostMapping("token")
    public ResponseEntity login(@RequestBody AuthRequestDTO dto) {
        return ResponseEntity.ok(userService.login(dto));
    }

    @Operation(summary = "Registration", description = "Registration for create new user")
    @PostMapping("registration")
    public ResponseEntity registration(@RequestBody AuthRequestDTO dto) {
        return ResponseEntity.ok(userService.registation(dto));
    }

}
