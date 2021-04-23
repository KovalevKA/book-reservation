package com.example.bookreservation.dto;

import lombok.Data;

import java.util.List;

@Data
public class BookDTO extends AbstractDTO {

    private String name;
    private String publishHouse;
    private int publishYear;
    private String description;
    private List<AuthorDTO> authors;
    private List<GenreDTO> genres;
    private List<TranslatorDTO> translators;
}
