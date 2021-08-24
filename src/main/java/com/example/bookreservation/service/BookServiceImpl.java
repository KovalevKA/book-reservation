package com.example.bookreservation.service;

import com.example.bookreservation.dto.BookDTO;
import com.example.bookreservation.entity.Book;
import com.example.bookreservation.mapper.AbstractMapper;
import com.example.bookreservation.repository.BookRepository;
import com.example.bookreservation.repository.GenreRepository;
import com.example.bookreservation.repository.TranslatorRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;

@Service
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class BookServiceImpl
        extends CommonServiceImpl<Book, BookDTO, AbstractMapper<Book, BookDTO>>
        implements BookService<Book, BookDTO> {

    public BookServiceImpl() {
        super();
        setClazz(Book.class);
    }

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private TranslatorRepository translatorRepository;
    @Autowired
    private AbstractMapper<Book, BookDTO> bookMapper;

    @Override
    public BookDTO editById(Long id, BookDTO data) throws EntityNotFoundException {
        Book book = entityManager.find(Book.class, id);
        book.setName(data.getName());
        book.setPublishHouse(data.getPublishHouse());
        book.setPublishYear(data.getPublishYear());
        book.setDescription(data.getDescription());

        SessionFactory

        book.getAuthorList().forEach(author -> {
            if (!data.getAuthors().contains(author)) {
                author.removeBook(book);
            }
        });
        book.getGenreList().forEach(genre -> {
            if (!resBook.getGenreList().contains(genre)) {
                genre.removeBook(book);
            }
        });
        book.getTranslatorList().forEach(translator -> {
            if (!resBook.getTranslatorList().contains(translator)) {
                translator.removeBook(book);
            }
        });
        return bookMapper.toDTO(book);
    }

    public List<BookDTO> findByParams(Boolean isReserved, String bookName,
        List<Long> listGenreId, List<Long> listAuthorId, List<Long> listTranslatorsId) {
        listAuthorId = listAuthorId.size() == 0 ? authorRepository.getAllIds() : listAuthorId;
        listGenreId = listGenreId.size() == 0 ? genreRepository.getAllIds() : listGenreId;
        listTranslatorsId =
            listTranslatorsId.size() == 0 ? translatorRepository.getAllIds() : listTranslatorsId;
        List<Book> books = bookRepository.getFreeByParams(bookName, listAuthorId, listGenreId,
            listTranslatorsId, new Date());
        if (!isReserved) {
            return bookMapper.toDTOs(books);
        }
        List<Book> resBooks = bookRepository.getReservByParams(bookName.toUpperCase(), listAuthorId,
            listGenreId, listTranslatorsId, new Date());
        books.addAll(resBooks);
        return bookMapper.toDTOs(books);
    }

}