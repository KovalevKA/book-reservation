package com.example.bookreservation.mapper;

import com.example.bookreservation.dto.ReservDTO;
import com.example.bookreservation.entity.Reserv;
import org.springframework.stereotype.Component;

@Component
public class ReservMapper implements AbstractMapper<Reserv, ReservDTO> {

    @Override
    public ReservDTO toDTO(Reserv reserv) {
        return null;
    }

    @Override
    public Reserv toEntity(ReservDTO reservDTO) {
        return null;
    }
}
