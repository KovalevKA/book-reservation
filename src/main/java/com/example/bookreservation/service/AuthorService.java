package com.example.bookreservation.service;

import com.example.bookreservation.dto.AuthorDTO;
import com.example.bookreservation.entity.Author;
import com.example.bookreservation.mapper.AuthorMapper;
import com.example.bookreservation.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService extends AbstractServiceImpl<Author, AuthorDTO, AuthorRepository, AuthorMapper> {

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private AuthorMapper authorMapper;

    public AuthorDTO getByName(String name){
        return authorMapper.toDTO(authorRepository.getByName(name));
    }

}
