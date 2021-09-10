package com.company.my_online_store.service;

import com.company.my_online_store.model.entity.User;

import java.util.Optional;

public interface UserService {
    User findByEmail(String email);

    Optional<User> findByUsername(String username);

    User save(User user);

    User update(User user);

}
