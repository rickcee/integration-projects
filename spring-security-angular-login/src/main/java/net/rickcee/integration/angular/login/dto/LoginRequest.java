package net.rickcee.integration.angular.login.dto;

import lombok.Data;

@Data
public class LoginRequest {

	private String username;
	private String password;
	private String token;

}