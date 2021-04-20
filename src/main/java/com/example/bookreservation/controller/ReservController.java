package com.example.bookreservation.controller;

import com.example.bookreservation.dto.BookDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("reservation")
public class ReservController {

    @PostMapping("make")
    public ResponseEntity<List<BookDTO>> makeReservation (
            @RequestParam(name = "clientId") Long clientId,
            @RequestParam(name = "listBooksId") List<Long> listBooksId
    ){
        return ResponseEntity.ok(null);
    }

    @PostMapping ("cancel")
    public ResponseEntity<List<BookDTO>> cancelReservation(
            @RequestParam(name = "clientId") Long clientId,
            @RequestParam(name = "listBooksId") List<Long> listBooksId
    ){
        return ResponseEntity.ok(null);
    }


}
