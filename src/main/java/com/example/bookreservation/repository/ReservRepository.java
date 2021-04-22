package com.example.bookreservation.repository;

import com.example.bookreservation.entity.Reserv;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservRepository extends JpaRepository<Reserv, Long> {
}
