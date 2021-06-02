package com.example.bookreservation.dto.requestBodyParams;

import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequestParamForCancelReservation {

    @NotNull
    private Long clientId;
    @NotNull
    private List<Long> listReservId;

}
