package com.example.bookreservation.service;

import com.example.bookreservation.dto.AuthorDTO;
import com.example.bookreservation.entity.Author;
import com.example.bookreservation.mapper.AuthorMapper;
import com.example.bookreservation.repository.AuthorRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthorService extends AbstractServiceImpl<Author, AuthorDTO, AuthorRepository, AuthorMapper> {
}
