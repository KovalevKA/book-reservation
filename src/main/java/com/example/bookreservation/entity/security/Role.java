package com.example.bookreservation.entity.security;

import com.example.bookreservation.entity.AbstractEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@AttributeOverride(name = "id", column = @Column(name = "role_id"))
@Table(name = "roles")
public class Role extends AbstractEntity {

    public static final String INDEX = "role";

    @Column(name = "name")
    private String name;
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles", cascade = {CascadeType.PERSIST,
            CascadeType.MERGE})
    private List<User> users;

}
