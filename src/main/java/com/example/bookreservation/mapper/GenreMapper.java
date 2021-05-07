package com.example.bookreservation.mapper;

import com.example.bookreservation.dto.GenreDTO;
import com.example.bookreservation.entity.Genre;
import org.springframework.stereotype.Component;

@Component
public class GenreMapper implements AbstractMapper<Genre, GenreDTO> {

  @Override
  public GenreDTO toDTO(Genre genre) {
    GenreDTO genreDTO = mapper.map(genre, GenreDTO.class);
    return genreDTO;
  }

  @Override
  public Genre toEntity(GenreDTO genreDTO) {
    Genre genre = mapper.map(genreDTO, Genre.class);
    return genre;
  }
}
