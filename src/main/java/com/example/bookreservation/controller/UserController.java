package com.example.bookreservation.controller;

import com.example.bookreservation.dto.security.UpdateUserDTO;
import com.example.bookreservation.service.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(
        UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UpdateUserDTO getUserInfo(@RequestHeader("Authorization") String header) {
        return userService.getUserInfo(header);
    }

    /**
     * TODO: add functions to logout and edit user
     * */

}
