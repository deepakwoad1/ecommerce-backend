package com.vuecart.auth_service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;

@Configuration
public class KeycloakAdminConfig {

	@Value("${keycloak.server-url}")
	private String serverUrl;

	@Value("${keycloak.admin-realm}")
	private String adminRealm;

	@Value("${keycloak.client-id}")
	private String clientId;

	@Value("${keycloak.client-secret}")
	private String clientSecret;

	@Bean
	public Keycloak keycloakAdminClient() {
		return KeycloakBuilder.builder().serverUrl(serverUrl).realm(adminRealm).clientId(clientId)
				.clientSecret(clientSecret).grantType(OAuth2Constants.CLIENT_CREDENTIALS).build();
	}
}
