package com.vuecart.auth_service.controller;

import java.util.Map;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vuecart.auth_service.dto.RegisterRequest;
import com.vuecart.auth_service.service.KeycloakService;
import com.vuecart.auth_service.service.KeycloakUserService;
import com.vuecart.auth_service.service.UserService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private final KeycloakUserService keycloakUserService;
	private final KeycloakService keycloakService;
	private final UserService userService;

	public AuthController(KeycloakUserService keycloakUserService, KeycloakService keycloakService,
			UserService userService) {

		this.keycloakUserService = keycloakUserService;
		this.keycloakService = keycloakService;
		this.userService = userService;
	}

	@PostMapping("/register")
	public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {
		keycloakUserService.registerUser(request);
		return ResponseEntity.ok(Map.of("message", "User registered successfully"));
	}

	@PostMapping("/register-new")
	@Transactional
	public ResponseEntity<?> registerNew(@RequestBody RegisterRequest request) {
		try {

			UUID keycloakId = keycloakService.createUser(request.getEmail(), request.getPassword(),
					request.getFirstName(), request.getLastName(), request.getUsername());

			userService.createUser(keycloakId, request.getEmail(),
					(request.getFirstName() + " " + request.getLastName()).trim(), request.getPhoneNumber());

			return ResponseEntity.ok("User registered successfully");

		} catch (Exception ex) {

			// compensate if DB fails
//			if (keycloakId != null) {
//				keycloakService.deleteUser(keycloakId);
//			}

			throw ex;
		}
	}

}
