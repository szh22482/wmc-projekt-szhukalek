package com.szhukalek.backend.persistence;

import com.szhukalek.backend.model.User;
import org.springframework.data.repository.Repository;


import java.util.List;

public interface UserRepository extends Repository<User, Long> {
    void save(User user);
    User findById(Long id);
    User findByEmail(String email);
    User findByNachname(String nachname);
    List<User> findAll();
}
