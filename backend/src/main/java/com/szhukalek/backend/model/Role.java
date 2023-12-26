package com.szhukalek.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "Tb_Rollen")
public class Role extends AbstractPersistable<Long> {
    private String role;

    @OneToMany
    private List<User> userList;
}
