package com.example.my_online_store.model.entity;

import org.springframework.security.core.GrantedAuthority;

public enum KnownAuthority implements GrantedAuthority {
    ROLE_ADMIN,
    ROLE_USER;

    @Override
    public String getAuthority() {
        return name();
    }
}