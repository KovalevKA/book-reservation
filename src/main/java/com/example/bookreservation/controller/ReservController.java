package com.example.bookreservation.controller;

import com.example.bookreservation.dto.ReservDTO;
import com.example.bookreservation.exception.ControllerExceptions;
import com.example.bookreservation.service.ReservService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("reservation")
public class ReservController extends ControllerExceptions {

    @Value("${books.forClient.count}")
    private Integer booksCountForClient;

    @Autowired
    private ReservService reservService;

    @GetMapping()
    public ResponseEntity<List<ReservDTO>> getListReservByClientId(@RequestParam(name = "clientId") Long clientId) {
        return ResponseEntity.ok(reservService.getReservationClientListById(clientId));
    }

    @PostMapping("make")
    public ResponseEntity<List<ReservDTO>> makeReservation(
            @RequestParam(name = "clientId") Long clientId,
            @RequestParam(name = "listBooksId") List<Long> listBooksId,
            @RequestParam(name = "dateTo") String dateTo
    ) throws ParseException {
        if (listBooksId.isEmpty())
            throw new IllegalArgumentException("No book to add");
        if (listBooksId.size() > booksCountForClient)
            throw new IllegalArgumentException("Books too match");
        Date date = new SimpleDateFormat("dd.MM.yyyy").parse(dateTo);
        if (date.compareTo(new Date()) <= 0)
            throw new IllegalArgumentException("Date isn't correct");
        return ResponseEntity.ok(reservService.make(clientId, listBooksId, date));
    }

    @PostMapping("cancel")
    public ResponseEntity<Integer> cancelReservation(
            @RequestParam(name = "clientId") Long clientId,
            @RequestParam(name = "reservId") List<Long> listReservId
    ) {
        return ResponseEntity.ok(reservService.cancel(clientId, listReservId));
    }


}
