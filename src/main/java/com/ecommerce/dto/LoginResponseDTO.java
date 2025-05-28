package com.ecommerce.dto;

import com.ecommerce.model.User;

public class LoginResponseDTO {
    private String token;
    private User user;
    private String message;
    
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public LoginResponseDTO(String token, User user, String message) {
		super();
		this.token = token;
		this.user = user;
		this.message = message;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public LoginResponseDTO() {
		super();
	}

}
