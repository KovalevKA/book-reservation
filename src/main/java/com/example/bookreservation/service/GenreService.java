package com.example.bookreservation.service;

import com.example.bookreservation.dto.GenreDTO;
import com.example.bookreservation.entity.Genre;
import com.example.bookreservation.mapper.AbstractMapper;
import com.example.bookreservation.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService extends
        CommonServiceImpl<Genre, GenreDTO, GenreRepository, AbstractMapper<Genre, GenreDTO>> {

  @Autowired
  private GenreRepository genreRepository;
  @Autowired
  private AbstractMapper<Genre, GenreDTO> genreMapper;

  public GenreService() {
    super();
    setClazz(Genre.class);
  }

  @Override
  public List<GenreDTO> getByNameLike(String name) {
    return genreMapper.toDTOs(genreRepository.findByNameContainsIgnoreCase(name));
  }

  @Override
  public Genre getByName(String name) {
    return genreRepository.findByNameIgnoreCase(name);
  }
}
