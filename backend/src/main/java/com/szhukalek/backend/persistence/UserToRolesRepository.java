package com.szhukalek.backend.persistence;

import com.szhukalek.backend.model.User;
import com.szhukalek.backend.model.UserToRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserToRolesRepository<UserToRolesId> extends Repository<UserToRoles, UserToRolesId> {

    void save(UserToRoles userToRoles);

    @Modifying
    @Transactional
    @Query("DELETE FROM UserToRoles ur WHERE ur.user = :user")
    void deleteByUser(@Param("user")User user);
}
