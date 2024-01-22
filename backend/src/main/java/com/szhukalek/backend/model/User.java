package com.szhukalek.backend.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tb_user")
public class User extends AbstractPersistable<Long> {

    private String vorname;
    private String nachname;
    private String email;
    private Boolean deleted;
    private Date created;
    private Date deletedDate;
    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<UserToRoles> roles;

    public List<String> getRoles() {
        return roles.stream().map(r -> r.getRole().getRole().toString()).collect(Collectors.toList());
    }

}
