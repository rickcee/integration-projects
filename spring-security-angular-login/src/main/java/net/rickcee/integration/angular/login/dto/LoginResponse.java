package net.rickcee.integration.angular.login.dto;

import java.util.ArrayList;
import java.util.Collection;

import lombok.Data;

@Data
public class LoginResponse {

	private String username;
	private String token;
	private Collection<String> roles = new ArrayList<>();

}