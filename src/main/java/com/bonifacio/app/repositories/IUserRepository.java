package com.bonifacio.app.repositories;

import com.bonifacio.app.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IUserRepository extends JpaRepository<User, UUID> {
    boolean existsByUsername(String userName);
    boolean existsByEmail(String name);
    Optional<User> findByUsername(String username);
}
