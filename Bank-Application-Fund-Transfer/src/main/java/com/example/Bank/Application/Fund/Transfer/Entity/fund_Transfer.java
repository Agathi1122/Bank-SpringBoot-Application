package com.example.Bank.Application.Fund.Transfer.Entity;

public class fund_Transfer {

	int from;
	int to;
	int amount;
	public fund_Transfer(int from, int to, int amount) {
		super();
		this.from = from;
		this.to = to;
		this.amount = amount;
	}
	public int getFrom() {
		return from;
	}
	public void setFrom(int from) {
		this.from = from;
	}
	public int getTo() {
		return to;
	}
	public void setTo(int to) {
		this.to = to;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	
}
