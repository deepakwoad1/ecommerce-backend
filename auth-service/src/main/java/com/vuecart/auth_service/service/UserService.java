package com.vuecart.auth_service.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.vuecart.auth_service.entities.AppUser;
import com.vuecart.auth_service.repositories.AppUserRepository;

@Service
public class UserService {

	private final AppUserRepository repository;

	public UserService(AppUserRepository repository) {
		this.repository = repository;
	}

	public AppUser createUser(UUID keycloakId, String email, String fullName, String phone) {

		// normalize email
		String normalizedEmail = email.toLowerCase().trim();

		// prevent duplicate DB user
		if (repository.existsByEmail(normalizedEmail)) {
			throw new IllegalStateException("User already exists with email: " + normalizedEmail);
		}

		AppUser user = new AppUser();
		user.setKeycloakId(keycloakId);
		user.setEmail(email);
		user.setFullName(fullName);
		user.setPhoneNumber(phone);

		return repository.save(user);
	}

	public AppUser getCurrentUser(UUID keycloakId) {
		return repository.findByKeycloakId(keycloakId).orElseThrow(() -> new RuntimeException("User not found"));
	}
}
