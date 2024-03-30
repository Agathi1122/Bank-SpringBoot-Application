package com.example.cartservice.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class cart {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Lineitem> item;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Lineitem> getItem() {
		return item;
	}

	public void setItem(List<Lineitem> item) {
		this.item = item;
	}

	public cart(Integer id, List<Lineitem> item) {
		super();
		this.id = id;
		this.item = item;
	}

	public cart() {
		super();
	}

	@Override
	public String toString() {
		return "cart [id=" + id + ", item=" + item + "]";
	}
	
	
}
