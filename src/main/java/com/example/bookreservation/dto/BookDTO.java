package com.example.bookreservation.dto;

import lombok.Data;

import java.util.Set;

@Data
public class BookDTO extends AbstractDTO {

  private String isbn;
  private String name;
  private String publishHouse;
  private int publishYear;
  private String description;
  private Set<AuthorDTO> authors;
  private Set<GenreDTO> genres;
  private Set<TranslatorDTO> translators;
}
