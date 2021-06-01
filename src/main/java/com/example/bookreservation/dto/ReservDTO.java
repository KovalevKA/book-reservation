package com.example.bookreservation.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservDTO extends AbstractDTO {

    private LocalDate reservationDate;
    private LocalDate reservationDateCancel;
    //private BookDTO book;

    public ReservDTO(ReservDTO reservDTO) {
        this.setId(reservDTO.getId());
        this.reservationDate = reservDTO.reservationDate;
        this.reservationDateCancel = reservDTO.reservationDateCancel;
        //this.book = reservDTO.book;
    }
}
