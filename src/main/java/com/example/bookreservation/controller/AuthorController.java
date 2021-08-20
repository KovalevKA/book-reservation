package com.example.bookreservation.controller;

import com.example.bookreservation.dto.AuthorDTO;
import com.example.bookreservation.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import java.util.List;

@Component
@Path("/authors")
@Produces(MediaType.APPLICATION_JSON_VALUE)
public class AuthorController {

  @Autowired
  private AuthorService authorService;

  @GET
  public List<AuthorDTO> getAllAuthors() {
    return authorService.getAll();
  }

  @GET
  @Path("find-name-like")
  public List<AuthorDTO> getAuthorByNameLike(@QueryParam("name") String name) {
    return authorService.getByNameLike(name);
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON_VALUE)
  public AuthorDTO addAuthor(AuthorDTO author) {
    return authorService.create(author);
  }

  @PATCH
  @Path("{id}")
  public AuthorDTO editAuthor(@PathParam("id") Long id, AuthorDTO author) {
    return authorService.editById(id, author);
  }

  @DELETE
  @Path("{id}")
  public void deleteAuthor(@PathParam("id") Long id) {
    authorService.deleteById(id);
  }
}
