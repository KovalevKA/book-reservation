package com.example.bookreservation.entity.security;

import com.example.bookreservation.entity.AbstractEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@AttributeOverride(name = "id", column = @Column(name = "role_id"))
@Table(name = "roles")
public class Role extends AbstractEntity {

    public static final String INDEX = "role";

    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private DefaultRoles name;

}
