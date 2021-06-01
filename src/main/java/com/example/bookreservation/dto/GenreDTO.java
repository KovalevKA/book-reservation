package com.example.bookreservation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class GenreDTO extends AbstractDTO {

    private String name;

    public GenreDTO(GenreDTO genreDTO) {
        this.name = genreDTO.name;
        this.setId(genreDTO.getId());
    }
}
