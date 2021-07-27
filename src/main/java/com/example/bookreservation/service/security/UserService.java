package com.example.bookreservation.service.security;

import com.example.bookreservation.dto.UserDTO;
import com.example.bookreservation.entity.security.User;
import com.example.bookreservation.mapper.AbstractMapper;
import com.example.bookreservation.repository.UserRepository;
import com.example.bookreservation.service.AbstractServiceImpl;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends
    AbstractServiceImpl<User, UserDTO, UserRepository, AbstractMapper<User, UserDTO>> {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> getByToken(String token) {
        return null;
    }
}
