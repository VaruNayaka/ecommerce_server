package com.ecommerce.Auno.request;

import java.util.HashSet;
import java.util.Set;

import com.ecommerce.Auno.model.Size;

public class CreateProductRequest {
	private String title;
	private String description;
	private int price;
	private int discountedPrice;
	private int discountPersent;
	private int quantity;
	private String brand;
	private String color;
	private Set<Size> sizes = new HashSet<>();
	private String imageUrl;
	private String TopLavelCategory;
	private String SecondLavelCategory;
	private String ThirdLavelCategory;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getDiscountedPrice() {
		return discountedPrice;
	}
	public void setDiscountedPrice(int discountedPrice) {
		this.discountedPrice = discountedPrice;
	}
	public int getDiscountPersent() {
		return discountPersent;
	}
	public void setDiscountPersent(int discountPersent) {
		this.discountPersent = discountPersent;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Set<Size> getSizes() {
		return sizes;
	}
	public void setSizes(Set<Size> sizes) {
		this.sizes = sizes;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getTopLavelCategory() {
		return TopLavelCategory;
	}
	public void setTopLavelCategory(String topLavelCategory) {
		TopLavelCategory = topLavelCategory;
	}
	public String getSecondLavelCategory() {
		return SecondLavelCategory;
	}
	public void setSecondLavelCategory(String secondLavelCategory) {
		SecondLavelCategory = secondLavelCategory;
	}
	public String getThirdLavelCategory() {
		return ThirdLavelCategory;
	}
	public void setThirdLavelCategory(String thirdLavelCategory) {
		ThirdLavelCategory = thirdLavelCategory;
	}
	public CreateProductRequest(String title, String description, int price, int discountedPrice, int discountPersent,
			int quantity, String brand, String color, Set<Size> sizes, String imageUrl, String topLavelCategory,
			String secondLavelCategory, String thirdLavelCategory) {
		super();
		this.title = title;
		this.description = description;
		this.price = price;
		this.discountedPrice = discountedPrice;
		this.discountPersent = discountPersent;
		this.quantity = quantity;
		this.brand = brand;
		this.color = color;
		this.sizes = sizes;
		this.imageUrl = imageUrl;
		TopLavelCategory = topLavelCategory;
		SecondLavelCategory = secondLavelCategory;
		ThirdLavelCategory = thirdLavelCategory;
	}
	
	public CreateProductRequest() {
		// TODO Auto-generated constructor stub
	}

}
