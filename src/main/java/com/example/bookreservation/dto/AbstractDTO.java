package com.example.bookreservation.dto;

import javax.persistence.MappedSuperclass;
import lombok.Data;

@MappedSuperclass
@Data
public class AbstractDTO {

  private String id;

}
