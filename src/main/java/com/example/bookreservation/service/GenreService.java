package com.example.bookreservation.service;

import com.example.bookreservation.dto.GenreDTO;
import com.example.bookreservation.entity.Genre;
import com.example.bookreservation.mapper.AbstractMapper;
import com.example.bookreservation.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class GenreService extends
    AbstractServiceImpl<Genre, GenreDTO, GenreRepository, AbstractMapper<Genre, GenreDTO>> {

    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private AbstractMapper<Genre, GenreDTO> genreMapper;

    @Override
    public Flux<GenreDTO> getByNameLike(String name) {
        //return genreMapper.toDTOs(genreRepository.findByNameContainsIgnoreCase(name));
        return genreRepository.findByNameContainsIgnoreCase(name)
            .map(genre -> new GenreDTO(genreMapper.toDTO(genre)));
    }

    @Override
    public Mono<Genre> getByName(String name) {
        return genreRepository.findByNameIgnoreCase(name);
    }
}
