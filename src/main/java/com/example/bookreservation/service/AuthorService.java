package com.example.bookreservation.service;

import com.example.bookreservation.dto.AuthorDTO;
import com.example.bookreservation.entity.Author;
import com.example.bookreservation.mapper.AbstractMapper;
import com.example.bookreservation.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AuthorService extends
    AbstractServiceImpl<Author, AuthorDTO, AuthorRepository, AbstractMapper<Author, AuthorDTO>> {

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private AbstractMapper<Author, AuthorDTO> authorMapper;

    @Override
    public Flux<AuthorDTO> getByNameLike(String name) {
        return authorRepository.findByNameContainsIgnoreCase(name)
            .map(author -> new AuthorDTO(authorMapper.toDTO(author)));
    }

    @Override
    public Mono<Author> getByName(String name) {
        return authorRepository.findByNameIgnoreCase(name);
    }
}
