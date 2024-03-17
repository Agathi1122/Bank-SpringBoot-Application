package com.example.Bank.Application.Fund.Transfer.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.Bank.Application.Fund.Transfer.Entity.customer;

@Repository
public interface customer_Repository extends CrudRepository<customer,Integer> {

}
