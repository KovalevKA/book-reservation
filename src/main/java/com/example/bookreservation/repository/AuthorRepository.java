package com.example.bookreservation.repository;

import com.example.bookreservation.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    Author findByNameIgnoreCase (String name);

    @Query("SELECT a FROM Author a WHERE UPPER(a.name) LIKE %:name%")
    List<Author> getAuthorByNameLike(@Param("name") String name);

    @Query("SELECT a.id FROM Author a")
    List<Long> getAllIdAuthors();
}
