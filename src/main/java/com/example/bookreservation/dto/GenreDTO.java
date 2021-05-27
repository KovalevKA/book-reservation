package com.example.bookreservation.dto;

import lombok.Data;

@Data
public class GenreDTO extends AbstractDTO {

    private final String name;

    public GenreDTO(GenreDTO genreDTO) {
        this.name = genreDTO.name;
        this.setId(genreDTO.getId());
    }
}
