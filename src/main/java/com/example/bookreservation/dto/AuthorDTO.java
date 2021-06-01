package com.example.bookreservation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDTO extends AbstractDTO {

    private String name;

    public AuthorDTO(AuthorDTO authorDTO) {
        this.name = authorDTO.name;
        this.setId(authorDTO.getId());
    }
}
