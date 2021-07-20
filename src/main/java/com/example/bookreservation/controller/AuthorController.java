package com.example.bookreservation.controller;

import com.example.bookreservation.dto.AuthorDTO;
import com.example.bookreservation.service.AuthorService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("authors")
public class AuthorController {

  @Autowired
  private AuthorService authorService;

  @GetMapping("test")
  public void ggg () throws Exception{
    Map<String, String> map = new HashMap<>();
    map.put("name", "nam");
    authorService.ggg("author",map);
  }

  @GetMapping
  public List<AuthorDTO> getAllAuthors() {
    return authorService.getAll();
  }

  @GetMapping("find-name-like")
  public List<AuthorDTO> getAuthorByNameLike(
      @RequestParam(name = "name") String name) {
    return authorService.getByNameLike(name);
  }

  @PostMapping
  public AuthorDTO addAuthor(@RequestBody AuthorDTO author) {
    return authorService.create(author);
  }

  @PatchMapping("{id}")
  public AuthorDTO editAuthor(
      @PathVariable Long id,
      @RequestBody AuthorDTO author) {
    return authorService.editById(id, author);
  }

  @DeleteMapping("{id}")
  public void deleteAuthor(@PathVariable Long id) {
    authorService.deleteById(id);
  }
}
