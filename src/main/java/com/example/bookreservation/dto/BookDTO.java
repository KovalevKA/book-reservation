package com.example.bookreservation.dto;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO extends AbstractDTO {

    private String name;
    private String publishHouse;
    private int publishYear;
    private String description;
    private Set<AuthorDTO> authors;
    private Set<GenreDTO> genres;
    private Set<TranslatorDTO> translators;

    public Set<AuthorDTO> getAuthors() {
        return authors;
    }

    public Set<GenreDTO> getGenres() {
        return genres;
    }

    public Set<TranslatorDTO> getTranslators() {
        return translators;
    }
}
