package com.example.bookreservation.dto.security;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "DTO for authorization")
public class AuthRequestDTO {

    @NotBlank
    @NotNull
    @Schema(description = "your login must be here")
    private String username;
    @NotBlank
    @NotNull
    @Schema(description = "your pass must be here")
    private String password;
}
