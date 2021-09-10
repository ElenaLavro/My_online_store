package com.company.my_online_store.model.auth;

import com.company.my_online_store.model.entity.enums.UserStatus;
import org.springframework.security.core.userdetails.User;

import java.util.EnumSet;

public class UserDetails extends User {
    private final com.company.my_online_store.model.entity.User source;

    public UserDetails(com.company.my_online_store.model.entity.User source) {
        super(source.getEmail(), source.getPassword(), source.getStatus() == UserStatus.ACTIVE, true, true, true, EnumSet.copyOf(source.getAuthorities().keySet()));
        this.source = source;
    }

    public com.company.my_online_store.model.entity.User getSource() {
        return source;
    }
}
