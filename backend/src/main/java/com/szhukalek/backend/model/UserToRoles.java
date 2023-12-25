package com.szhukalek.backend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "Tb_User2Rollen")
public class UserToRoles {

    @Id
    @ManyToOne
    private User user;

    @Id
    @ManyToOne
    private Role role;
}
