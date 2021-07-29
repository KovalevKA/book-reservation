package com.example.bookreservation.dto.security;

import com.example.bookreservation.dto.AbstractDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserDTO extends AbstractDTO {

    private String username;
    private String password;
    private String firstName;
    private String lastName;

}
