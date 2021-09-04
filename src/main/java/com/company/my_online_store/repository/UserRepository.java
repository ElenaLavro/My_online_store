package com.company.my_online_store.repository;


import com.company.my_online_store.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,String> {
    User findByEmail(String email);
    Collection<User> findAllByRole(String role);
}
