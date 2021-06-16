package com.example.bookreservation.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {

    private Long bookId;
    private String name;
    private String publishHouse;
    private int publishYear;
    private String description;
    private List<AuthorDTO> authors = new ArrayList<>();
    private List<GenreDTO> genres = new ArrayList<>();
    private List<TranslatorDTO> translators = new ArrayList<>();

}
