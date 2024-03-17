package com.example.Bank.Application.Fund.Transfer.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.Bank.Application.Fund.Transfer.Entity.account;
import com.example.Bank.Application.Fund.Transfer.Exception.Exception_class;
import com.example.Bank.Application.Fund.Transfer.Repository.account_repository;

@Service
public class account_service {

	@Autowired
	account_repository repo;

	public ResponseEntity<account> createAccount(account creationOfAccount) {
		account create_account = repo.save(creationOfAccount);
		return new ResponseEntity<account>(create_account, HttpStatus.CREATED);
	}

	public List<account> getAllAccount() {
		List<account> acc = (List<account>) repo.findAll();
		return acc;
	}

	public account getAccount(int account_id) {
		Optional<account> acc = repo.findById(account_id);
		if (acc.isEmpty()) {
			throw new Exception_class("Account not exists !! " + account_id);
		}

		account getAccount = acc.get();

		if (getAccount == null) {
			throw new Exception_class("Customer not exists !! " + account_id);
		}
		return getAccount;
	}

	public account updateAccount(int account_id, account acc_details) {
		Optional<account> acc_data = repo.findById(account_id);
		account acc = acc_data.get();
		if (acc == null) {
			throw new Exception_class("Account not exists !! " + account_id);
		}
		//acc.setAccountNumber(acc_details.getAccountNumber());
		acc.setBalance(acc_details.getBalance());
		acc.setType(acc_details.getType());
		return repo.save(acc);
	}

	public account deleteAccount(int account_id) {
		Optional<account> acc_data = repo.findById(account_id);
		account delete_acc = acc_data.get();
		if (delete_acc == null) {
			throw new Exception_class("Account with id  " + account_id + " not found");
		}
		repo.deleteById(account_id);
		return delete_acc;
	}

	public account fundTransfer(int from_acc, int to_acc, double amount) {
		Optional<account> fromAcc = repo.findByAccountNumber(from_acc);
		if (fromAcc.isEmpty()) {
			throw new Exception_class("Account not found with accountNumer-# " + fromAcc);
		}

		if (amount <= 0) {
			throw new Exception_class("Amount is either negative or zero , " + amount + " so u cannot transfer");
		}

		if (from_acc == to_acc) {
			throw new Exception_class("Both account have same amount of money so can't transfer");
		}

		Optional<account> toAcc = repo.findByAccountNumber(to_acc);

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

		repo.save(fromAccountData);

		account toAccountData = toAcc.get();

		double toAccountCurrentBalance = toAccountData.getBalance();

		double newToAccountData_afterfund_transfered_currentBalance = toAccountCurrentBalance + amount;

		toAccountData.setBalance(newToAccountData_afterfund_transfered_currentBalance);

		repo.save(toAccountData);

		return fromAccountData;

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
	}

}
