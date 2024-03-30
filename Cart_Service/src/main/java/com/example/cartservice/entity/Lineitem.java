package com.example.cartservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Lineitem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer itemId;
	 
	private Integer productId;
	
	private String productname;
	
	private Integer quantity;
	
	private Double price;

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Lineitem(Integer itemId, Integer productId, String productname, Integer quantity, Double price) {
		super();
		this.itemId = itemId;
		this.productId = productId;
		this.productname = productname;
		this.quantity = quantity;
		this.price = price;
	}

	public Lineitem() {
		super();
	}

	@Override
	public String toString() {
		return "Lineitem [itemId=" + itemId + ", productId=" + productId + ", productname=" + productname
				+ ", quantity=" + quantity + ", price=" + price + "]";
	}
	
	
	
	
	

}
