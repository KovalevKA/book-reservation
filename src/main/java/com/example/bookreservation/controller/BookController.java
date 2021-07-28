package com.example.bookreservation.controller;

import com.example.bookreservation.dto.BookDTO;
import com.example.bookreservation.entity.Book;
import com.example.bookreservation.service.BookService;
import com.example.bookreservation.service.elasticSearch.AbstractElasticSearchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import java.io.IOException;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.elasticsearch.rest.RestStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("books")
public class BookController {

    @Autowired
    private BookService<Book, BookDTO> bookService;
    @Autowired
    private AbstractElasticSearchService<BookDTO> elasticSearchService;

    @Operation(summary = "Paginated output of all books", description = "Paginated output of all books")
    @GetMapping
    public List<BookDTO> getBooksWithPagination(
        @RequestParam(defaultValue = "10") @Parameter(description = "Number of books per page") Integer countInPage,
        @RequestParam(defaultValue = "1") @Parameter(description = "Number of page") Integer page)
        throws IOException {
        return elasticSearchService.getWithPagination(countInPage, page);
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

    @Operation(summary = "Addating a book", description = "The book is added to both the database and elasticsearch")
    @PostMapping()
    public BookDTO addBook(
        @RequestBody @Parameter(description = "Description book") BookDTO data
    ) throws Exception {
        String id = bookService.create(data).getId();
        data.setId(id);
        elasticSearchService.add(data);
        return data;
    }

    @GetMapping("{id}")
    public BookDTO getInfoAboutBookByBookId(@PathVariable("id") Long id) {
        return bookService.getById(id);
    }

    @Operation(summary = "Updating a book", description = "The book is updated to both the database and elasticsearch")
    @PostMapping("{id}")
    public BookDTO editBook(
        @Valid @PathVariable("id") @NotNull @NotBlank @Parameter(description = "Id book for updating") Long id,
        @RequestBody @Parameter(description = "Description book") BookDTO data
    )
        throws Exception {
        elasticSearchService.update(id.toString(), data);
        return bookService.editById(id, data);
    }

    @Operation(summary = "Delete a book", description = "The book is deleted to both the database and elasticsearch")
    @DeleteMapping("{id}")
    public RestStatus deleteBook(
        @PathVariable @Parameter(description = "Id book for delete") Long id
    ) throws Exception {
        bookService.deleteById(id);
        return elasticSearchService.delete(id.toString());
    }
}
