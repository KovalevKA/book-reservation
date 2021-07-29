package com.example.bookreservation.security;

import com.example.bookreservation.entity.security.User;
import com.example.bookreservation.security.jwt.JwtUser;
import com.example.bookreservation.security.jwt.JwtUserFactory;
import com.example.bookreservation.service.security.UserService;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public JwtUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getByName(username);

        if (user == null) {
            throw new EntityNotFoundException("User with username " + username + " not found");
        }

        JwtUser jwtUser = JwtUserFactory.create(user);

        return jwtUser;
    }
}
