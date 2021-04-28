package com.example.bookreservation.service;

import com.example.bookreservation.dto.GenreDTO;
import com.example.bookreservation.entity.Genre;
import com.example.bookreservation.mapper.GenreMapper;
import com.example.bookreservation.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService extends AbstractServiceImpl<Genre, GenreDTO, GenreRepository, GenreMapper> {

    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private GenreMapper genreMapper;

    public List<GenreDTO> getGenresByNameLike (String name){
        return genreMapper.toDTOs(genreRepository.getGenresByNameLike(name));
    }

    @Override
    public Genre getByName(String name) {
        return genreRepository.getByName(name);
    }
}
