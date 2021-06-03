package com.example.bookreservation.service;

import static java.time.LocalDate.now;

import com.example.bookreservation.dto.ReservDTO;
import com.example.bookreservation.entity.Reserv;
import com.example.bookreservation.mapper.AbstractMapper;
import com.example.bookreservation.repository.BookRepository;
import com.example.bookreservation.repository.ReservRepository;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ReservService {

    @Autowired
    private ReservRepository reservRepository;
    @Autowired
    private AbstractMapper<Reserv, ReservDTO> reservMapper;
    @Autowired
    private BookRepository bookRepository;

    public Flux<ReservDTO> getReservationClientListById(Long id) {
        return reservRepository.findByClientIdAndAndReservationDateCancelGreaterThan(id, now())
            .map(reserv -> reservMapper.toDTO(reserv));
    }

    public Flux<ReservDTO> checkReservedBooksByBookId(List<Long> ids) {
        return reservRepository
            .findByBookIdInAndReservationDateCancelGreaterThanEqual(ids, now())
            .map(reserv -> reservMapper.toDTO(reserv));
    }

    public Flux<ReservDTO> make(Long id, List<Long> bookIds, LocalDate dateTo)
        throws IllegalArgumentException {
        return bookRepository.getFreeBooksByListId(bookIds)
            .map(book -> new Reserv(id, book.getBookId(), dateTo))
            .flatMap(reserv -> reservRepository.save(reserv))
            .map(reservMapper::toDTO)
            ;
    }

    public Mono<Long> cancel(Long id, List<Long> listReservId) throws IllegalArgumentException {
        return reservRepository
            .findByIdInAndReservationDateCancelGreaterThanEqual(id, listReservId, now())
            .map(reserv -> {
                reserv.setReservationDateCancel(now());
                return reservRepository.save(reserv);
            }).count();
    }
}
