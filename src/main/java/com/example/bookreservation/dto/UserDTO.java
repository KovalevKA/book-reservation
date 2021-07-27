package com.example.bookreservation.dto;

import com.example.bookreservation.entity.security.Role;
import java.util.Set;
import lombok.Data;

@Data
public class UserDTO extends AbstractDTO {

    private String login;
    private char[] pass;
    private Set<Role> roles;

    public UserDTO(String login, char[] pass) {
        this.login = login;
        this.pass = pass;
    }
}
