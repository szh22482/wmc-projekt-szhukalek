package com.szhukalek.backend.dto;

import com.szhukalek.backend.model.User;
import com.szhukalek.backend.model.UserToRoles;
import lombok.Builder;

import java.sql.Date;
import java.util.List;

@Builder
public record UserDTO(Long id,
                      String email,
                      String firstname,
                      String lastname,
                      Date created,
                      List<String> roles,
                      String password) {

    public static UserDTO fromEntity(User entity) {
        return UserDTO.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .firstname(entity.getVorname())
                .lastname(entity.getNachname())
                .created(entity.getCreated())
                .roles(entity.getRoles())
                .password(entity.getPassword())
                .build();
    }
}
