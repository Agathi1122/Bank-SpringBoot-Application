package com.example.Bank.Application.Fund.Transfer.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int accountNumber;
	
	@Enumerated(EnumType.STRING)
	account_Type type;
	
	double balance;
	
	

	public account() {
		super();
	}



	public account(account_Type type, double balance) {
		super();
		this.type = type;
		this.balance = balance;
	}



	public account(int accountNumber, account_Type type, double balance) {
		super();
		this.accountNumber = accountNumber;
		this.type = type;
		this.balance = balance;
	}



	public int getAccountNumber() {
		return accountNumber;
	}



	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}



	public account_Type getType() {
		return type;
	}



	public void setType(account_Type type) {
		this.type = type;
	}



	public double getBalance() {
		return balance;
	}



	public void setBalance(double balance) {
		this.balance = balance;
	}



	@Override
	public String toString() {
		return "account [accountNumber=" + accountNumber + ", type=" + type + ", balance=" + balance + "]";
	}
	
	
	
}
