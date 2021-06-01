package com.example.bookreservation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class GenreDTO extends AbstractDTO {

    private Long genre_id;
    private String name;

}
