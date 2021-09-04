package com.site.my_online_store.model.entity.enums;

import org.springframework.security.core.GrantedAuthority;

public enum KnownAuthority implements GrantedAuthority {
    ROLE_ADMIN,
    ROLE_USER;

    @Override
    public String getAuthority() {
        return name();
    }
}