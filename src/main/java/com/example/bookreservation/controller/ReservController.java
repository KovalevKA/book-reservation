package com.example.bookreservation.controller;

import com.example.bookreservation.dto.BookDTO;
import com.example.bookreservation.exception.ControllerExceptions;
import com.example.bookreservation.service.ReservService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("reservation")
public class ReservController extends ControllerExceptions {

    @Autowired
    private ReservService reservService;

    @PostMapping("make")
    public ResponseEntity<List<BookDTO>> makeReservation(
            @RequestParam(name = "clientId") Long clientId,
            @RequestParam(name = "listBooksId") List<Long> listBooksId
    ) {
        return ResponseEntity.ok(null);
    }

    @PostMapping("cancel")
    public ResponseEntity<List<BookDTO>> cancelReservation(
            @RequestParam(name = "clientId") Long clientId,
            @RequestParam(name = "listBooksId") List<Long> listBooksId
    ) {
        return ResponseEntity.ok(null);
    }


}
