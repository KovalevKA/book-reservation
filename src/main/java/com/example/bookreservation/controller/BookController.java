package com.example.bookreservation.controller;

import com.example.bookreservation.dto.BookDTO;
import com.example.bookreservation.entity.Book;
import com.example.bookreservation.service.BookService;
import com.example.bookreservation.service.elasticSearch.BookElasticSerachServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import javax.ws.rs.*;
import java.util.List;

@Component
@Path("/books")
@Produces(MediaType.APPLICATION_JSON_VALUE)
public class BookController {

    @Autowired
    private BookService<Book, BookDTO> bookService;
    @Autowired
    private BookElasticSerachServiceImpl service;

    /*@POST
    @Path("search")
    public List<BookDTO> getBooksWhithParams(
            @RequestParam(name = "isReserved", defaultValue = "false", required = false) boolean isReserved,
            @RequestParam(name = "bookName", defaultValue = "", required = false) String bookName,
            @RequestParam(name = "listGenreId", defaultValue = "", required = false) List<Long> listGenreId,
            @RequestParam(name = "listAuthorId", defaultValue = "", required = false) List<Long> listAuthorId,
            @RequestParam(name = "listTranslatorsId", defaultValue = "", required = false) List<Long> listTranslatorsId
    ) {
        *//*return bookService
            .findByParams(isReserved, bookName.toUpperCase(), listGenreId, listAuthorId,
                listTranslatorsId);*//*
        return service.search("");
    }*/

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    public BookDTO addBook(BookDTO data) throws Exception {
        String id = bookService.create(data).getId();
        data.setId(id);
        return data;
    }

    @GET
    @Path("{id}")
    public BookDTO getInfoAboutBookByBookId(@PathParam("id") Long id) {
        return bookService.getById(id);
    }

    @POST
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    public BookDTO editBook(@PathParam("id") Long id, BookDTO data) {
        return bookService.editById(id, data);
    }

    @DELETE
    @Path("{id}")
    public void deleteBook(
            @PathParam("id") Long id) {
        bookService.deleteById(id);
    }
}
