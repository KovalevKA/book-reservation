package com.example.bookreservation.dto.security;

import com.example.bookreservation.dto.AbstractDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO extends AbstractDTO {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private List<RoleDTO> roles;

    public UserDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

