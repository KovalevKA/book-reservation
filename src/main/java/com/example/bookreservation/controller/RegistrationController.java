package com.example.bookreservation.controller;

import com.example.bookreservation.dto.UserDTO;
import com.example.bookreservation.service.security.UserAuthenticationService;
import com.example.bookreservation.service.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("registration")
public class RegistrationController {

    private UserAuthenticationService userAuthenticationService;
    @Autowired
    private UserService userService;

    @PostMapping
    public String register(
        @RequestParam("username") final String username,
        @RequestParam("password") final char[] password) {
        userService.create(
            new UserDTO(username, password)
        );
        return login(username, password);
    }

    @PostMapping("/login")
    String login(
        @RequestParam("username") final String username,
        @RequestParam("password") final char[] password) {
        return userAuthenticationService
            .login(username, String.valueOf(password))
            .orElseThrow(() -> new RuntimeException("invalid login and/or password"));
    }


}
