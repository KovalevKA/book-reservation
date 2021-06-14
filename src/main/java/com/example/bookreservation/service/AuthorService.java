package com.example.bookreservation.service;

import com.example.bookreservation.dto.AuthorDTO;
import com.example.bookreservation.entity.Author;
import com.example.bookreservation.mapper.AbstractMapper;
import com.example.bookreservation.repository.AuthorRepository;
import javax.transaction.Transactional;
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
            .map(author -> authorMapper.toDTO(author));
    }

    @Override
    public Mono<Author> getByName(String name) {
        return authorRepository.findByNameIgnoreCase(name);
    }

    @Transactional
    @Override
    public Mono<AuthorDTO> editById(Long id, final AuthorDTO authorDTO) {
        return authorRepository.findByAuthorId(id)
            .map(author -> {
                author.setName(authorDTO.getName());
                return author;
            })
            .flatMap(author -> this.authorRepository.save(author))
            .map(authorMapper::toDTO)
            ;
    }

    @Override
    public Flux<AuthorDTO> getByBookId(Long id) {
        return authorRepository.findByBookId(id).map(authorMapper::toDTO);
    }
}
