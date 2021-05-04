package com.example.bookreservation.controller;

import com.example.bookreservation.dto.ReservDTO;
import com.example.bookreservation.service.ReservService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("reservation")
public class ReservController {

    @Autowired
    private ReservService reservService;

    @GetMapping()
    public ResponseEntity<List<ReservDTO>> getListReservByClientId(
            @RequestParam(name = "clientId") Long clientId) {
        return ResponseEntity.ok(reservService.getReservationClientListById(clientId));
    }

    @PostMapping("check")
    public ResponseEntity<List<ReservDTO>> checkReservedBooksByBookId(
            @RequestParam(name = "listBooksId") List<Long> listBooksId) {
        return ResponseEntity.ok(reservService.checkReservedBooksByBookId(listBooksId));
    }

    @PostMapping("make")
    public ResponseEntity<List<ReservDTO>> makeReservation(
            @RequestParam(name = "clientId") Long clientId,
            @RequestParam(name = "listBooksId") List<Long> listBooksId,
            @RequestParam(name = "dateTo") String dateTo){
        return ResponseEntity.ok(reservService.make(clientId, listBooksId, dateTo));
    }

    @PostMapping("cancel")
    public ResponseEntity<Integer> cancelReservation(
            @RequestParam(name = "clientId") Long clientId,
            @RequestParam(name = "reservId") List<Long> listReservId) {
        return ResponseEntity.ok(reservService.cancel(clientId, listReservId));
    }


}
