package com.example.bookreservation.controller;

import com.example.bookreservation.dto.security.AuthRequestDTO;
import com.example.bookreservation.entity.security.User;
import com.example.bookreservation.security.jwt.JwtTokenProvider;
import com.example.bookreservation.service.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("auth")
public class AuthentificationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity login(@RequestBody AuthRequestDTO dto) {
        try {
            String userName = dto.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, dto.getPassword()));
            User user = userService.getByName(userName);
            if (user == null) throw new UsernameNotFoundException("User with username " + userName + " not found");
            String token = jwtTokenProvider.createToken(userName, user.getRoles());
            Map<Object, Object> response = new HashMap<>();
            response.put("username", userName);
            response.put("token", token);
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }

    }

}
