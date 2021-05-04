package com.example.bookreservation.service;

import com.example.bookreservation.dto.AuthorDTO;
import com.example.bookreservation.entity.Author;
import com.example.bookreservation.mapper.AbstractMapper;
import com.example.bookreservation.mapper.AuthorMapper;
import com.example.bookreservation.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService extends AbstractServiceImpl<Author, AuthorDTO, AuthorRepository, AuthorMapper> {

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private AbstractMapper<Author, AuthorDTO> authorMapper;

    public List<AuthorDTO> getAuthorByNameLike(String name) {
        return authorMapper.toDTOs(authorRepository.findAuthorsByNameContainsIgnoreCase(name));
    }

    @Override
    public Author getByName(String name) {
        return authorRepository.findByNameIgnoreCase(name);
    }

}
