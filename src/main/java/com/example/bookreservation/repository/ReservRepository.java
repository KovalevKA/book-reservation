package com.example.bookreservation.repository;

import com.example.bookreservation.entity.Reserv;
import java.util.Date;
import java.util.List;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface ReservRepository extends ReactiveCrudRepository<Reserv, Long> {


    @Query("SELECT reserv.* FROM reserv WHERE reserv.client_id = :id AND "
        + "reserv.reservation_date_cancel > :date")
    Flux<Reserv> findByClientIdAndAndReservationDateCancelGreaterThan(Long id, Date date);

    @Query("SELECT reserv.* FROM reserv WHERE client_id = :id AND "
        + "reserv_id IN :ids AND "
        + "reserv.reservation_date_cancel >= :date")
    Flux<Reserv> findByIdInAndReservationDateCancelGreaterThanEqual(Long id, List<Long> ids,
        Date date);

    @Query("SELECT reserv.* FROM reserv WHERE reserv.book_id IN :ids "
        + "AND reserv.reservation_date_cancel >= :date")
    Flux<Reserv> findByBookIdInAndReservationDateCancelGreaterThanEqual(List<Long> ids,
        Date date);
}
