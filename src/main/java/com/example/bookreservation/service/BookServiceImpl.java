package com.example.bookreservation.service;

import com.example.bookreservation.dto.BookDTO;
import com.example.bookreservation.entity.Book;
import com.example.bookreservation.mapper.AbstractMapper;
import com.example.bookreservation.repository.AuthorRepository;
import com.example.bookreservation.repository.BookRepository;
import com.example.bookreservation.repository.GenreRepository;
import com.example.bookreservation.repository.TranslatorRepository;
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
    private AuthorRepository authorRepository;
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private TranslatorRepository translatorRepository;
    @Autowired
    private AbstractMapper<Book, BookDTO> bookMapper;

    @Override
    public BookDTO editById(Long id, BookDTO data) throws EntityNotFoundException {
        Book book = bookRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        if (!data.getName().isEmpty()) {
            book.setName(data.getName());
        }
        if (!data.getPublishHouse().isEmpty()) {
            book.setPublishHouse(data.getPublishHouse());
        }
        if (data.getPublishYear() != 0) {
            book.setPublishYear(data.getPublishYear());
        }
        if (!data.getDescription().isEmpty()) {
            book.setDescription(data.getDescription());
        }

        return bookMapper.toDTO(bookRepository.saveAndFlush(book));
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