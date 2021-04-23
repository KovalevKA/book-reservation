package com.example.bookreservation.mapper;

import com.example.bookreservation.dto.BookDTO;
import com.example.bookreservation.entity.Book;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class BookMapper implements AbstractMapper<Book, BookDTO> {

    private ModelMapper mapper = new ModelMapper();
    private AuthorMapper authorMapper = new AuthorMapper();
    private GenreMapper genreMapper = new GenreMapper();
    private TranslatorMapper translatorMapper = new TranslatorMapper();

    @Override
    public BookDTO toDTO(Book book) {
        BookDTO bookDTO = mapper.map(book, BookDTO.class);
        bookDTO.setAuthors(authorMapper.toDTOs(book.getAuthorList()));
        bookDTO.setGenres(genreMapper.toDTOs(book.getGenreList()));
        bookDTO.setTranslators(translatorMapper.toDTOs(book.getTranslatorList()));
        return bookDTO;
    }

    @Override
    public Book toEntity(BookDTO bookDTO) {
        Book book = mapper.map(bookDTO, Book.class);
        book.setAuthorList(authorMapper.toEntities(bookDTO.getAuthors()));
        book.setGenreList(genreMapper.toEntities(bookDTO.getGenres()));
        book.setTranslatorList(translatorMapper.toEntities(bookDTO.getTranslators()));
        return book;
    }
}
