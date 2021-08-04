package com.example.bookreservation.service.security;

import com.example.bookreservation.dto.security.AuthRequestDTO;
import com.example.bookreservation.dto.security.UpdateUserDTO;
import com.example.bookreservation.dto.security.UserDTO;
import com.example.bookreservation.entity.security.User;
import com.example.bookreservation.service.CommonService;
import java.util.Map;

public interface UserService extends CommonService<User, UserDTO> {

    Map<Object, Object> login(AuthRequestDTO dto);

    Map<Object, Object> registation(AuthRequestDTO dto);

    UpdateUserDTO getUserInfo(String header);
}
