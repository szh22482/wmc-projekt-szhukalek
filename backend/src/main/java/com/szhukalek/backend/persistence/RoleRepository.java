package com.szhukalek.backend.persistence;

import com.szhukalek.backend.model.ERoles;
import com.szhukalek.backend.model.Role;
import org.springframework.data.repository.Repository;


import java.util.List;

public interface RoleRepository extends Repository<Role, Long> {
    void save(Role role);
    List<Role> findAll();
    Role findById(Long id);
    Role findByName(ERoles name);
}
