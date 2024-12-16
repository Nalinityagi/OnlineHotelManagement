package com.hotelbooking.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
	@NotBlank
	private String email;
	
	@NotBlank
	private String password;

	public Object getEmail() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getPassword() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
