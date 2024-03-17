package com.example.Bank.Application.Fund.Transfer.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.Bank.Application.Fund.Transfer.Repository.account_repository;
import com.example.Bank.Application.Fund.Transfer.Repository.customer_Repository;

import jakarta.transaction.Transactional;

import com.example.Bank.Application.Fund.Transfer.Entity.*;
import com.example.Bank.Application.Fund.Transfer.Exception.Exception_class;

@Service
public class customer_service {

	// private static List<customer> customers = new ArrayList<customer>();

	// static int counter=2000;

	@Autowired
	customer_Repository customerrepository;

	@Autowired
	account_repository accountrepository;

	public ResponseEntity<customer> createcustomer(customer customerData) {
		customer createcustomer = customerrepository.save(customerData);
		return new ResponseEntity<customer>(createcustomer, HttpStatus.CREATED);
	}

	public List<customer> getcustomers() {

		List<customer> allcustomers = (List<customer>) customerrepository.findAll();
		// System.out.println("customer 11111--->"+customers);

		return allcustomers;
	}

	public customer getcustomer(int customerid) {

		Optional<customer> allcustomers = customerrepository.findById(customerid);
		if (allcustomers.isEmpty()) {

			throw new Exception_class("customer doesn't exists" + customerid);
		}

		customer customer = allcustomers.get();

		if (customer == null) {
			throw new Exception_class("customer doesn't exists" + customerid);
		}

		return customer;
	}

	public customer updatecustomer(int id, customer cust) {

		Optional<customer> customers = customerrepository.findById(id);

		if (customers.isEmpty()) {

			throw new Exception_class("customer doesn't exists-->#" + id);
		}

		customer customer = customers.get();

		if (customer == null) {

			throw new Exception_class("customer doesn't exists" + id);

		}

		customer.setFirstName(cust.getFirstName());

		customer.setLastName(cust.getLastName());

		customer.setEmail(cust.getEmail());

		return customerrepository.save(customer);
	}

	public customer deletecustomer(int customer_id) {

		Optional<customer> customers = customerrepository.findById(customer_id);

		customer deletecustomer = customers.get();
		// System.out.println("Delete customers: "+deletecustomer);

		if (deletecustomer == null) {

			throw new Exception_class("Employee with id-->" + customer_id + " not found");

		}

		customerrepository.deleteById(customer_id);

		return deletecustomer;
	}

	@Transactional
	public customer create(int id, account account) {

		customer customer = customerrepository.findById(id).orElse(null);

		if (customer == null) {

			throw new Exception_class("customer doesn't exists-->" + id);
		}

		if (customer != null) {
			Set<account> accounts = customer.getAccounts();
			accounts.add(account);
			return customerrepository.save(customer);
		} else {
			return customer;
		}
	}

	public account fundTransfer(int from_acc, int to_acc, double amount) {
		Optional<account> fromAcc = accountrepository.findByAccountNumber(from_acc);
		if (fromAcc.isEmpty()) {
			throw new Exception_class("Account not found with accountNumer-# " + fromAcc);
		}

		if (amount <= 0) {
			throw new Exception_class("Amount is either negative or zero , " + amount + " so u cannot transfer");
		}

		if (from_acc == to_acc) {
			throw new Exception_class("Both account have same amount of money so can't transfer");
		}

		Optional<account> toAcc = accountrepository.findByAccountNumber(to_acc);

		if (toAcc.isEmpty()) {
			throw new Exception_class("Account not found with accountNumer-# " + to_acc);
		}

		account fromAccountData = fromAcc.get();

		double newFromAccountData_afterfund_transfered_currentBalance;

		double fromAcc_currentBalance = fromAccountData.getBalance();

		if (fromAcc_currentBalance >= amount) {
			newFromAccountData_afterfund_transfered_currentBalance = fromAcc_currentBalance - amount;
			fromAccountData.setBalance(newFromAccountData_afterfund_transfered_currentBalance);
		} else {
			throw new Exception_class(
					"Account has not enough balance ! so u can't transfer amount and available balance is "
							+ fromAcc_currentBalance);

		}

		accountrepository.save(fromAccountData);

		account toAccountData = toAcc.get();

		double toAccountCurrentBalance = toAccountData.getBalance();

		double newToAccountData_afterfund_transfered_currentBalance = toAccountCurrentBalance + amount;

		toAccountData.setBalance(newToAccountData_afterfund_transfered_currentBalance);

		accountrepository.save(toAccountData);

		return fromAccountData;
	}

	/**
	 * Optional<Account> fromAcc repository.findById(fromAccount);
	 * 
	 * if(fromAcc.isEmpty()) { throw new NotFoundException("Account not found with
	 * accnumber#"+ fromAccount); } Account fromAccountDetailsfromAcc.get(), double
	 * BalanceincurrentAccount;
	 * 
	 * double fromAccCurrentbalance fromAccountDetail.getBalance();
	 * 
	 * if(fromAccCurrentbalance>=amount) (
	 * 
	 * BalanceincurrentAccount fromAccCurrentbalance amount;
	 * fromAccountDetail.setBalance(BalanceincurrentAccount); } else {
	 * 
	 * throw new BalanceNotFoundException("Account has not Enough balance to
	 * Transfer,so u cannot transfer amount:
	 * 
	 * remaining balance is-->" +fromAccCurrentbalance);)
	 * 
	 * System.out.println("***fromAccount Details"+fromAccountDetail);
	 * repository.save(fromAccountDetail);
	 * 
	 * Optional<Account> toAcc repository.findById(toAccount); if(toAcc.isEmpty()) {
	 * throw new NotFoundException("Account not found with accnumber-s toAccount); }
	 * Account toAccountDetail=toAcc.get();
	 * 
	 * double toAccCurrentbalance toaccountDetail.getBalance(); double
	 * BalanceintoAccount toAccCurrentbalance amount;
	 * 
	 * toAccountDetail.setBalance(BalanceintoAccount);
	 * 
	 * System.out.println("***ToAccount Details"+toAccountDetail);
	 * repository.save(toAccountDetail);
	 * 
	 * return fromAccountDetail,
	 * 
	 */

	@Transactional
	public customer saveCustomer(int id, account acc) {
		customer cust = customerrepository.findById(id).orElse(null);
		if (cust != null) {
			Set<account> accounts = cust.getAccounts();
			accounts.add(acc);

			return customerrepository.save(cust);
		} else {
			return cust;
		}

	}
}
