package com.example.bookreservation.entity.security;

import com.example.bookreservation.entity.AbstractEntity;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@AttributeOverride(name = "id", column = @Column(name = "role_id"))
@Table(name = "roles")
public class Role extends AbstractEntity {

    public static final String INDEX = "role";

    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private String name;

}
