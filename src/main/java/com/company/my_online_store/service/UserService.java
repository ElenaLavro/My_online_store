package com.company.my_online_store.service;

import com.company.my_online_store.model.entity.User;

public interface UserService {
    User findByEmail(String email);

    User findByUsername(String username);

    User save(User user);

    User update(User user);

}
