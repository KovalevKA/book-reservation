package com.example.bookreservation.dto;

import java.util.Set;
import lombok.Data;

@Data
public class BookDTO extends AbstractDTO {

  private String name;
  private String publishHouse;
  private int publishYear;
  private String description;
  private Set<AuthorDTO> authors;
  private Set<GenreDTO> genres;
  private Set<TranslatorDTO> translators;
}
