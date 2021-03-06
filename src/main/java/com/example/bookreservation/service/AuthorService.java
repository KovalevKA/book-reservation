package com.example.bookreservation.service;

import com.example.bookreservation.dto.AuthorDTO;
import com.example.bookreservation.entity.Author;
import com.example.bookreservation.mapper.AbstractMapper;
import com.example.bookreservation.repository.AuthorRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService extends
    AbstractServiceImpl<Author, AuthorDTO, AuthorRepository, AbstractMapper<Author, AuthorDTO>> {

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private AbstractMapper<Author, AuthorDTO> authorMapper;

    @Override
    public List<AuthorDTO> getByNameLike(String name) {
        return authorMapper.toDTOs(authorRepository.findByNameContainsIgnoreCase(name));
    }

    @Override
    public Author getByName(String name) {
        return authorRepository.findByNameIgnoreCase(name);
    }

}
