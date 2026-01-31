package com.vuecart.auth_service.service;

import java.util.UUID;

import javax.ws.rs.core.Response;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import org.springframework.stereotype.Service;

@Service
public class KeycloakService {

	private final Keycloak keycloak;
	private final String realm = "ecommerce-realm";

	public KeycloakService(Keycloak keycloak) {
		this.keycloak = keycloak;
	}

	public UUID createUser(String email, String password, String firstName, String lastName, String userName) {

		UserRepresentation user = new UserRepresentation();
		user.setUsername(userName);
		user.setEmail(email);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEnabled(true);
		user.setEmailVerified(false);

		Response response = keycloak.realm(realm).users().create(user);

		if (response.getStatus() != 201) {
			throw new RuntimeException("Keycloak user creation failed");
		}

		String userId = response.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1");

		// set password
		CredentialRepresentation credential = new CredentialRepresentation();
		credential.setType(CredentialRepresentation.PASSWORD);
		credential.setValue(password);
		credential.setTemporary(false);

		keycloak.realm(realm).users().get(userId).resetPassword(credential);

		return UUID.fromString(userId);
	}
}
