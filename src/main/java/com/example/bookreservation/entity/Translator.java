package com.example.bookreservation.entity;

import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(value = "translator")
public class Translator {

    @Id
    @Column(value = "translator_id")
    private Long translatorId;
    @Column
    private String name;
}
