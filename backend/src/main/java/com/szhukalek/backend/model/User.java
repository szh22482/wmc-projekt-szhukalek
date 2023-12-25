package com.szhukalek.backend.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.sql.Date;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "Tb_User")
public class User extends AbstractPersistable<Long> {
    private String Vorname;
    private String Nachname;
    private String email;
    private Boolean deleted;
    private Date created;
    private Date deletedDate;
    private String password;

    @OneToMany
    private List<UserToRoles> roles;

}
