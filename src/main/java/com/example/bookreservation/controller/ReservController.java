package com.example.bookreservation.controller;

import com.example.bookreservation.dto.ReservDTO;
import com.example.bookreservation.service.ReservService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("reservation")
public class ReservController {

    @Autowired
    private ReservService reservService;

    @GetMapping()
    public Flux<ReservDTO> getListReservByClientId(
        @RequestParam(name = "clientId") Long clientId) {
        return reservService.getReservationClientListById(clientId);
    }

    @PostMapping("check")
    public Flux<ReservDTO> checkReservedBooksByBookId(
        @RequestParam(name = "listBooksId") List<Long> listBooksId) {
        return reservService.checkReservedBooksByBookId(listBooksId);
    }

    @PostMapping("make")
    public Flux<ReservDTO> makeReservation(
        @RequestParam(name = "clientId") Long clientId,
        @RequestParam(name = "listBooksId") List<Long> listBooksId,
        @RequestParam(name = "dateTo") String dateTo) {
        return reservService.make(clientId, listBooksId, dateTo);
    }

    @PostMapping("cancel")
    public Mono<Long> cancelReservation(
        @RequestParam(name = "clientId") Long clientId,
        @RequestParam(name = "reservId") List<Long> listReservId) {
        return reservService.cancel(clientId, listReservId);
    }
}
