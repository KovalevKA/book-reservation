package com.example.bookreservation.controller;

import com.example.bookreservation.dto.BookDTO;
import com.example.bookreservation.dto.ReservDTO;
import com.example.bookreservation.exception.ControllerExceptions;
import com.example.bookreservation.service.ReservService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("reservation")
public class ReservController extends ControllerExceptions {

    @Autowired
    private ReservService reservService;

    @Value("books.counts.forclient")
    Integer booksCountForClient;

    @GetMapping
    public ResponseEntity<List<ReservDTO>> getListReservByClientId(@RequestParam Long id) {
        return ResponseEntity.ok(reservService.getReservationClientListById(id));
    }

    @PostMapping("make")
    public ResponseEntity<List<BookDTO>> makeReservation(
            @RequestParam(name = "clientId") Long clientId,
            @RequestParam(name = "listBooksId") List<Long> listBooksId
    ) {
        if (listBooksId.isEmpty())
            throw new IllegalArgumentException("No book to add");
        if (listBooksId.size() > booksCountForClient)
            throw new IllegalArgumentException("Books too match");
        return ResponseEntity.ok(reservService.make(clientId, listBooksId));
    }

    @PostMapping("cancel")
    public ResponseEntity<List<BookDTO>> cancelReservation(
            @RequestParam(name = "clientId") Long clientId,
            @RequestParam(name = "listBooksId") List<Long> listBooksId
    ) {
        return ResponseEntity.ok(null);
    }


}
