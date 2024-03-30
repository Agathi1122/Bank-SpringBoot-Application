package com.example.cartservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cartservice.entity.cart;

@Repository
public interface cart_Repository extends JpaRepository<cart,Integer> {

}
