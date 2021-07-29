package com.example.bookreservation.mapper;

import com.example.bookreservation.dto.security.UserDTO;
import com.example.bookreservation.entity.security.User;
import java.lang.reflect.Type;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements AbstractMapper<User, UserDTO> {

    @Override
    public UserDTO toDTO(User user) {
        return mapper.map(user, (Type) UserDTO.class);
    }

    @Override
    public User toEntity(UserDTO userDTO) {
        return mapper.map(userDTO, (Type) User.class);
    }
}
