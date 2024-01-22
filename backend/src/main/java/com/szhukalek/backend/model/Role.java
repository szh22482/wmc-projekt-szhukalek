package com.szhukalek.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "tb_rollen")
public class Role extends AbstractPersistable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ERoles role;

    @OneToMany(mappedBy = "role", fetch = FetchType.EAGER)
    private List<UserToRoles> userList;
}
