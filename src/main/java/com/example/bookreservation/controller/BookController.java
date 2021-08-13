package com.example.bookreservation.controller;

import com.example.bookreservation.dto.BookDTO;
import com.example.bookreservation.entity.Book;
import com.example.bookreservation.service.BookService;
import com.example.bookreservation.service.elasticSearch.BookElasticSerachServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
@RestController
@RequestMapping("books")
public class BookController {

    @Autowired
    private BookService<Book, BookDTO> bookService;
    @Autowired
    private BookElasticSerachServiceImpl service;

    @PostMapping("search")
    public List<BookDTO> getBooksWhithParams(
        @RequestParam(name = "isReserved", defaultValue = "false", required = false) boolean isReserved,
        @RequestParam(name = "bookName", defaultValue = "", required = false) String bookName,
        @RequestParam(name = "listGenreId", defaultValue = "", required = false) List<Long> listGenreId,
        @RequestParam(name = "listAuthorId", defaultValue = "", required = false) List<Long> listAuthorId,
        @RequestParam(name = "listTranslatorsId", defaultValue = "", required = false) List<Long> listTranslatorsId
    ) {
        /*return bookService
            .findByParams(isReserved, bookName.toUpperCase(), listGenreId, listAuthorId,
                listTranslatorsId);*/
        return service.search("");
    }

    @Operation(summary = "Addating a book", description = "The book is added to both the database and elasticsearch")
    @PostMapping()
    public BookDTO addBook(
            @RequestBody @Parameter(description = "Description book") BookDTO data) throws Exception {
        /*String id = bookService.create(data).getId();
        data.setId(id);*/
        service.add(data);
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
            @RequestBody @Parameter(description = "Description book") BookDTO data) {
        return bookService.editById(id, data);
    }

    @Operation(summary = "Delete a book", description = "The book is deleted to both the database and elasticsearch")
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteBook(
            @PathVariable @Parameter(description = "Id book for delete") Long id) {
        bookService.deleteById(id);
    }
}
