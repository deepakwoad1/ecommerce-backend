package com.vuecart.auth_service.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vuecart.auth_service.entities.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByKeycloakId(UUID keycloakId);

    boolean existsByEmail(String email);
}
