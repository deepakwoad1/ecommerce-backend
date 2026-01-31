package com.vuecart.auth_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {

	@NotBlank
	private String username;

	@Email
	@NotBlank
	private String email;

	@NotBlank
	@Size(min = 8)
	private String password;

	@NotBlank
	private String firstName;

	@NotBlank
	private String lastName;

	@NotBlank
	@Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid phone number")
	private String phoneNumber;
}
