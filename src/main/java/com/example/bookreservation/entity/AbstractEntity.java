package com.example.bookreservation.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.ReadOnlyProperty;

@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@Data
public class AbstractEntity {

  @Id
  @ReadOnlyProperty
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
}
