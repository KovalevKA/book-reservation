package com.example.bookreservation.repository;

import com.example.bookreservation.entity.Reserv;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservRepository extends JpaRepository<Reserv, Long> {

  List<Reserv> findReservsByClientIdAndAndReservationDateCancelGreaterThan(Long id, Date date);

  List<Reserv> findReservsByIdInAndReservationDateCancelGreaterThanEqual(List<Long> ids, Date date);

  List<Reserv> findReservsByBookIdInAndReservationDateCancelGreaterThanEqual(List<Long> ids,
      Date date);
}
