package com.example.Bank.Application.Fund.Transfer.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Bank.Application.Fund.Transfer.Entity.account;
import com.example.Bank.Application.Fund.Transfer.Entity.customer;
import com.example.Bank.Application.Fund.Transfer.Service.customer_service;

@RestController
public class customer_Controller {

	@Autowired
	customer_service customerservice;

	@PostMapping("/api/customers")
	public ResponseEntity<customer> createcustomer(@RequestBody customer cust){
	
	    return customerservice.createcustomer(cust);
	}


	@PostMapping(value = "api/customers/{id}", consumes= "application/json")
	public customer create(@PathVariable int id, @RequestBody account acc) {

	    return customerservice.create(id, acc);
	}

	
	@GetMapping("/api/customers")
	public List<customer> getcustomers() {

	List<customer> customers =customerservice.getcustomers();
	//System.out.println("Controller sen"+customers); 

	   return customers;
	}
	
	
	@GetMapping(value = "/api/customers/{customer_id}")
	public customer getAccount (@PathVariable int customer_id)
	{
		// @Pathvariable refers to local variable

	    customer customer = customerservice.getcustomer(customer_id);
	    return customer; 
	}

	
	@PutMapping("/api/customers/{customer_id}") 
	public customer updatecustomer(@PathVariable int customer_id,@RequestBody customer cust) {

	  customer customerUpdate = customerservice.updatecustomer(customer_id,cust);

	   return customerUpdate;
	}

	
	@DeleteMapping("/api/customers/{customer_id}")
	public customer deletecustomer(@PathVariable int customer_id) {

     	customer customerDelete =customerservice.deletecustomer(customer_id);

	    return customerDelete;

	}

	@PostMapping(value = "/customeraccount/{id}", consumes= "application/json")
	public ResponseEntity<Object> savecustomer(@PathVariable int id, @RequestBody account account)
	{

	    customer entity = customerservice.saveCustomer(id, account);

	    if (entity != null)

	
	    	return ResponseEntity.status(HttpStatus.CREATED).body("Maaped Account with customer successfully");
	    else {
	    	return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer Doesnot Exist");
	    }
	}
	
}
