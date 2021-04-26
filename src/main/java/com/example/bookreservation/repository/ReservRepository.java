package com.example.bookreservation.repository;

import com.example.bookreservation.entity.Reserv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservRepository extends JpaRepository<Reserv, Long> {

    /*@Query("SELECT COUNT(Reserv.id) FROM Reserv WHERE Reserv.client.id = :id AND Reserv.reservationDateCancel > CURRENT_DATE")
    int getCountReservedBookByClientId(@Param("id") long id);
*/

    @Query("SELECT r FROM Reserv r WHERE r.client.id = :id AND r.reservationDateCancel >= CURRENT_DATE")
    List<Reserv> getReservationClientListById(@Param("id") Long id);

}
