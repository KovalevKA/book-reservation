package com.example.bookreservation.dto;

import com.example.bookreservation.entity.security.Role;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserDTO extends AbstractDTO {

    private String username;
    private char[] password;
    private Set<Role> roles = new HashSet<>();

    public UserDTO(String username, char[] password) {
        this.username = username;
        this.password = password;
    }
}
