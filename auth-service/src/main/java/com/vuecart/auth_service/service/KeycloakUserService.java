package com.vuecart.auth_service.service;

import java.util.List;

import javax.ws.rs.core.Response;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import com.vuecart.auth_service.dto.RegisterRequest;

@Service
public class KeycloakUserService {

	private final Keycloak keycloak;
	private final String realm = "ecommerce-realm";

	public KeycloakUserService(Keycloak keycloak) {
		this.keycloak = keycloak;
	}

	public void registerUser(RegisterRequest request) {

		UserRepresentation user = new UserRepresentation();
		user.setUsername(request.getUsername());
		user.setEmail(request.getEmail());
		user.setFirstName(request.getFirstName());
		user.setLastName(request.getLastName());
		user.setEnabled(true);
		user.setEmailVerified(false);

		CredentialRepresentation password = new CredentialRepresentation();
		password.setType(CredentialRepresentation.PASSWORD);
		password.setValue(request.getPassword());
		password.setTemporary(false);

		user.setCredentials(List.of(password));

		Response response = keycloak.realm(realm).users().create(user);

		try {
			int status = response.getStatus();

			if (status == 409) {
				throw new RuntimeException("User already exists");
			}

			if (status != 201) {
				throw new RuntimeException("Failed to create user in Keycloak. Status: " + status);
			}

		} finally {
			response.close();
		}
	}
}
