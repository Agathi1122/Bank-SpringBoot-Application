package com.example.Bank.Application.Fund.Transfer.Entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int customerId;
	private String firstName;
	private String lastName;
	private String email;
	
	@ManyToMany(cascade =CascadeType.ALL , fetch = FetchType.LAZY)
	@JoinTable(name="customer_accounts", joinColumns= @JoinColumn(name="customer_id"),
	inverseJoinColumns= @JoinColumn(name= "account_id"))
	private Set<account> accounts;
	
	
	
	
	public customer() {
		super();
	}
	public customer(int customerId, String firstName, String lastName, String email, Set<account> accounts) {
		super();
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.accounts = accounts;
	}
	public customer(int customerId, String firstName, String lastName, String email) {
		super();
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
	public Set<account> getAccounts() {
		return accounts;
	}
	public void setAccounts(Set<account> accounts) {
		this.accounts = accounts;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "customer [customerId=" + customerId + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", accounts=" + accounts + "]";
	}
	
	

}
