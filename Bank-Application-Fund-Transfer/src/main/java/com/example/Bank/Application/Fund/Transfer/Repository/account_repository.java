package com.example.Bank.Application.Fund.Transfer.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.Bank.Application.Fund.Transfer.Entity.account;

@Repository
public interface account_repository extends JpaRepository<account,Integer> {
	@Query(value="select * from account where account.account_number =?1", nativeQuery= true)
	public Optional<account> findByAccountNumber(int account_number);

}
