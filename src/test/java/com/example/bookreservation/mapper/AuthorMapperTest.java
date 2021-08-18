package com.example.bookreservation.mapper;

import com.example.bookreservation.dto.AuthorDTO;
import com.example.bookreservation.entity.Author;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class AuthorMapperTest {

    AuthorMapper authorMapper = new AuthorMapper();

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void toDTO() {
        assertEquals(authorMapper.toDTO(new Author()), new AuthorDTO());
    }

    @Test
    void toEntity() {
    }
}