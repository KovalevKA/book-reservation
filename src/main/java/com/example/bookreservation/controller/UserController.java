package com.example.bookreservation.controller;

import com.example.bookreservation.entity.security.User;
import com.example.bookreservation.service.security.UserAuthenticationService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserAuthenticationService userAuthenticationService;

    @GetMapping("/current")
     User getCurrent(@AuthenticationPrincipal final User user) {
        return user;
    }

    @GetMapping("/logout")
    boolean logout(@AuthenticationPrincipal final User user) {
        userAuthenticationService.logout(user);
        return true;
    }


}
