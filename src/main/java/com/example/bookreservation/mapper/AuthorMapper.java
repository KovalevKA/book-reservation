package com.example.bookreservation.mapper;

import com.example.bookreservation.dto.AuthorDTO;
import com.example.bookreservation.entity.Author;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;

@Component
public class AuthorMapper implements AbstractMapper<Author, AuthorDTO> {

    private ModelMapper mapper = new ModelMapper();

    @Override
    public AuthorDTO toDTO(Author entity) {
        AuthorDTO dto = mapper.map(entity, (Type) AuthorDTO.class);
        return dto;
    }

    @Override
    public Author toEntity(AuthorDTO dto) {
        Author entity = mapper.map(dto, (Type) Author.class);
        return entity;
    }
}
