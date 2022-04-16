package com.yegecali.security.repository;

import com.yegecali.security.model.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserAuth, Long> {
    UserAuth findByUsername(String username);
    UserAuth findByName(String name);
}
