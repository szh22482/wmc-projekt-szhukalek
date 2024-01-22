package com.szhukalek.backend.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;

import java.io.Serializable;
import java.util.Objects;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserToRolesId implements Serializable {

    private Long userId;
    private Long roleId;

    public UserToRolesId() {
    }

    public UserToRolesId(long userId, Long roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public Long getUserId() {
        return userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserToRolesId that = (UserToRolesId) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(roleId, that.roleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, roleId);
    }
}

