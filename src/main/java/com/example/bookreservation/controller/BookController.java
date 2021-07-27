package com.example.bookreservation.controller;

import com.example.bookreservation.dto.BookDTO;
import com.example.bookreservation.entity.Book;
import com.example.bookreservation.service.BookService;
import com.example.bookreservation.service.elasticSearch.BookElasticSearchService;
import java.io.IOException;
import java.util.List;
import org.elasticsearch.rest.RestStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("books")
public class BookController {

    @Autowired
    private BookService<Book, BookDTO> bookService;
    @Autowired
    private BookElasticSearchService bookBookDTOAbstractElasticSearchService;

    @GetMapping
    public List<BookDTO> getBooksWithPagination(
        @RequestParam(defaultValue = "10") Integer countInPage,
        @RequestParam(defaultValue = "1") Integer page) throws IOException {
        return bookBookDTOAbstractElasticSearchService.getWithPagination(countInPage, page);
    }

    @PostMapping("search")
    public List<BookDTO> getBooksWhithParams(
        @RequestParam(name = "isReserved", defaultValue = "false", required = false) boolean isReserved,
        @RequestParam(name = "bookName", defaultValue = "", required = false) String bookName,
        @RequestParam(name = "listGenreId", defaultValue = "", required = false) List<Long> listGenreId,
        @RequestParam(name = "listAuthorId", defaultValue = "", required = false) List<Long> listAuthorId,
        @RequestParam(name = "listTranslatorsId", defaultValue = "", required = false) List<Long> listTranslatorsId
    ) {
        return bookService
            .findByParams(isReserved, bookName.toUpperCase(), listGenreId, listAuthorId,
                listTranslatorsId);
    }

    @PostMapping()
    public BookDTO addBook(@RequestBody BookDTO data) throws Exception {
        String id = bookService.create(data).getId();
        data.setId(id);
        bookBookDTOAbstractElasticSearchService.add(data);
        return data;
    }

    @GetMapping("{id}")
    public BookDTO getInfoAboutBookByBookId(@PathVariable("id") Long id) {
        return bookService.getById(id);
    }

    @PostMapping("{id}")
    public BookDTO editBook(@PathVariable("id") Long id, @RequestBody BookDTO data)
        throws Exception {
        bookBookDTOAbstractElasticSearchService.update(id.toString(), data);
        return bookService.editById(id, data);
    }

    @DeleteMapping("{id}")
    public RestStatus deleteBook(@PathVariable Long id) throws Exception {
        bookService.deleteById(id);
        return bookBookDTOAbstractElasticSearchService.delete(id.toString());
    }
}
