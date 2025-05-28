package com.ecommerce.dto;

public class ProfileCompletionDTO {
    private long mobile;
    private String address;
	public long getMobile() {
		return mobile;
	}
	public void setMobile(long mobile) {
		this.mobile = mobile;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public ProfileCompletionDTO(long mobile, String address) {
		super();
		this.mobile = mobile;
		this.address = address;
	}
	public ProfileCompletionDTO() {
		super();
	}

}
