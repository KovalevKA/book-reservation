package com.example.bookreservation.service;

import com.example.bookreservation.dto.BookDTO;
import com.example.bookreservation.entity.Book;
import com.example.bookreservation.mapper.BookMapper;
import com.example.bookreservation.repository.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookService extends AbstractServiceImpl<Book, BookDTO, BookRepository, BookMapper>{
}
