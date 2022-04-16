package com.yegecali.security.repository;

import com.yegecali.security.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolaRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
