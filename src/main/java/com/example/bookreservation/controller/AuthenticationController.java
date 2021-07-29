package com.example.bookreservation.controller;

import com.example.bookreservation.dto.security.AuthRequestDTO;
import com.example.bookreservation.security.jwt.JwtTokenProvider;
import com.example.bookreservation.service.security.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private UserService userService;

    public AuthenticationController(
        UserService userService,
        AuthenticationManager authenticationManager,
        JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Operation(summary = "Login", description = "Authorization for getting token")
    @PostMapping("login")
    public ResponseEntity login(@RequestBody AuthRequestDTO dto) {
        try {
            authenticationManager
                .authenticate(
                    new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword())
                );
            return ResponseEntity.ok(userService.login(dto));
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @Operation(summary = "Registration", description = "Registartion for create new user")
    @PostMapping("registation")
    public ResponseEntity registration(@RequestBody AuthRequestDTO dto) {
        return ResponseEntity.ok(userService.registation(dto));
    }

}
