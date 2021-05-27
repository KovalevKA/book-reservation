package com.example.bookreservation.dto;

import java.time.LocalDate;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReservDTO extends AbstractDTO {

    private final LocalDate reservationDate;
    private final LocalDate reservationDateCancel;
    //private BookDTO book;

    public ReservDTO(ReservDTO reservDTO) {
        this.setId(reservDTO.getId());
        this.reservationDate = reservDTO.reservationDate;
        this.reservationDateCancel = reservDTO.reservationDateCancel;
        //this.book = reservDTO.book;
    }
}
