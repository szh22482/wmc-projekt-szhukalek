package com.szhukalek.backend.dto;

import com.szhukalek.backend.model.UserToRoles;

import java.sql.Date;
import java.util.List;

public record UserDTO(Long id, String email, String firstname,
                      String lastname, Date created, List<String> roles) {
}
