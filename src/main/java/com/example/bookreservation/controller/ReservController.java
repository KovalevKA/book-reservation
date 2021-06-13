package com.example.bookreservation.controller;

import com.example.bookreservation.dto.ReservDTO;
import com.example.bookreservation.dto.requestBodyParams.RequestParamForCancelReservation;
import com.example.bookreservation.dto.requestBodyParams.RequestParamForCheckReservedBooksByBookId;
import com.example.bookreservation.dto.requestBodyParams.RequestParamForMakeReservetion;
import com.example.bookreservation.service.ReservService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
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
        @RequestBody RequestParamForCheckReservedBooksByBookId requestParam) {
        return reservService.checkReservedBooksByBookId(requestParam.getListBooksId());
    }

    @PostMapping("make")
    public Flux<ReservDTO> makeReservation(
        @RequestBody RequestParamForMakeReservetion requestParam) {
        return reservService
            .make(requestParam.getClientId(), requestParam.getListBooksId(),
                requestParam.getDateTo());
    }

    @PostMapping("cancel")
    public Mono<Long> cancelReservation(
        @RequestBody RequestParamForCancelReservation requestParam) {
        return reservService.cancel(requestParam.getClientId(), requestParam.getListReservId());
    }
}
