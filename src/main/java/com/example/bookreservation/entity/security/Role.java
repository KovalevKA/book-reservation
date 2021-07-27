package com.example.bookreservation.entity.security;

import com.example.bookreservation.entity.AbstractEntity;
import java.util.Set;
import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@AttributeOverride(name = "id", column = @Column(name = "role_id"))
@Table(name = "role")
public class Role extends AbstractEntity {

    public static final String INDEX = "role";

    @Column(name = "name")
    private String name;
    @ManyToMany(fetch = FetchType.LAZY, targetEntity = User.class, cascade = {CascadeType.PERSIST,
        CascadeType.MERGE})
    @JoinTable(name = "user_role",
        joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "role_id")},
        inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")})
    private Set<User> users;

}
