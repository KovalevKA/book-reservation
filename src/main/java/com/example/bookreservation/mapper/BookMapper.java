package com.example.bookreservation.mapper;

import com.example.bookreservation.dto.BookDTO;
import com.example.bookreservation.entity.Author;
import com.example.bookreservation.entity.Book;
import com.example.bookreservation.entity.Genre;
import com.example.bookreservation.entity.Translator;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Set;

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
        Set<Author> authors = authorMapper.toEntities(bookDTO.getAuthors());
        authors.forEach(author -> author.addBook(book));
        Set<Genre> genres = genreMapper.toEntities(bookDTO.getGenres());
        genres.forEach(genre -> genre.addBook(book));
        Set<Translator> translators = translatorMapper.toEntities(bookDTO.getTranslators());
        translators.forEach(translator -> translator.addBook(book));
        return book;
    }

}
