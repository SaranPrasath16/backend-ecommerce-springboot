package com.ecommerce.dto;

public class GoogleLoginRequestDTO {
	private String idToken;

	public String getIdToken() {
		return idToken;
	}

	public void setIdToken(String idToken) {
		this.idToken = idToken;
	}

	public GoogleLoginRequestDTO(String idToken) {
		super();
		this.idToken = idToken;
	}

	public GoogleLoginRequestDTO() {
		super();
	}

}
