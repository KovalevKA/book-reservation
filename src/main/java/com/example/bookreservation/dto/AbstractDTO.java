package com.example.bookreservation.dto;

import lombok.Data;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
public class AbstractDTO {

    private Long id;

}
