package com.company.my_online_store.repository;

import com.company.my_online_store.model.entity.UserAuthority;
import com.company.my_online_store.model.entity.enums.KnownAuthority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.EnumSet;
import java.util.Set;
import java.util.stream.Stream;

@Repository
public interface AuthorityRepository extends JpaRepository<UserAuthority, KnownAuthority> {
    Set<KnownAuthority> ADMIN_AUTHORITIES = EnumSet.of(KnownAuthority.ROLE_USER, KnownAuthority.ROLE_ADMIN);

    Stream<UserAuthority> findAllByIdIn(Set<KnownAuthority> ids);
}
