package com.ecommerce.dto;

import java.util.List;

public class ProductGetResponseDTO {
    private String category;
    private String productId;
    private String ProductName;
    private String productDescription;
    private double productPrice;
    private List<String> imageUrls;
    private Object reviews;
    
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public Object getReviews() {
		return reviews;
	}
	public void setReviews(Object reviews) {
		this.reviews = reviews;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getProductName() {
		return ProductName;
	}
	public void setProductName(String productName) {
		ProductName = productName;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
	public List<String> getImageUrls() {
		return imageUrls;
	}
	public void setImageUrls(List<String> imageUrls) {
		this.imageUrls = imageUrls;
	}

	public ProductGetResponseDTO() {
		super();
	}
	public ProductGetResponseDTO(String category, String productId, String productName, String productDescription,
			double productPrice, List<String> imageUrls, Object reviews) {
		super();
		this.category = category;
		this.productId = productId;
		ProductName = productName;
		this.productDescription = productDescription;
		this.productPrice = productPrice;
		this.imageUrls = imageUrls;
		this.reviews = reviews;
	}


}
