package com.example.bookreservation.mapper;

import com.example.bookreservation.dto.AbstractDTO;
import com.example.bookreservation.dto.BookDTO;
import com.example.bookreservation.entity.*;
import com.example.bookreservation.service.AbstractService;
import com.example.bookreservation.service.AuthorService;
import com.example.bookreservation.service.GenreService;
import com.example.bookreservation.service.TranslatorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

@Component
public class BookMapper implements AbstractMapper<Book, BookDTO> {

    private ModelMapper mapper = new ModelMapper();
    private AuthorMapper authorMapper = new AuthorMapper();
    private GenreMapper genreMapper = new GenreMapper();
    private TranslatorMapper translatorMapper = new TranslatorMapper();

    @Autowired
    private AuthorService authorService;
    @Autowired
    private GenreService genreService;
    @Autowired
    private TranslatorService translatorService;


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

        Set<Author> authors = (Set<Author>) getNestedRecords(bookDTO.getAuthors(), authorService);
        authors.forEach(author -> author.addBook(book));

        Set<Genre> genres = (Set<Genre>) getNestedRecords(bookDTO.getGenres(), genreService);
        genres.forEach(genre -> genre.addBook(book));

        Set<Translator> translators = (Set<Translator>) getNestedRecords(bookDTO.getTranslators(), translatorService);
        translators.forEach(translator -> translator.addBook(book));

        return book;
    }


    private Set<?> getNestedRecords(Set<? extends AbstractDTO> dataList, AbstractService service) {
        Set<? super AbstractEntity> outSet = new HashSet<>();
        dataList.forEach(data -> {
            try {
                Field field = data.getClass().getDeclaredField("name");
                field.setAccessible(true);
                if (service.getByName((String) field.get(data)) == null){
                    data.setId(service.create(data).getId());
                }
                outSet.add(service.getByName((String) field.get(data)));
                field.setAccessible(false);
            }catch (IllegalAccessException e){
                new IllegalArgumentException("No access to field");
            } catch (NoSuchFieldException e) {
                new NoSuchFieldException("No field available");
            }
        });
        return outSet;
    }


}
