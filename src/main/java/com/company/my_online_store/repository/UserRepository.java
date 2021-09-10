package com.company.my_online_store.repository;


import com.company.my_online_store.model.entity.User;
import com.company.my_online_store.model.entity.enums.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    User findByEmail(String email);

    Optional<User> findByNickname(String nickname);

    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);

    void deleteByEmail(String email);

    @Query("update User u set u.status = :status where u.email = :email")
    @Modifying
    void chageStatusByEmail(String email, UserStatus status);

}
