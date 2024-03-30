package com.example.cartservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.cartservice.entity.cart;
import com.example.cartservice.exception.Exception_class;
import com.example.cartservice.repository.cart_Repository;

@Service
public class cartService {
	
	@Autowired
	cart_Repository repo;
	
	public List<cart> getall_Carts()
	{
		List<cart> allcarts=repo.findAll();
		return allcarts;
	}
	
	public ResponseEntity<cart> create_Cart(cart cartdata)
	{
		cart createCart= repo.save(cartdata);
		return new ResponseEntity<cart>(createCart,HttpStatus.CREATED);
	}
	
	public cart get_Cart(int cartid) {
		Optional<cart> cartdata= repo.findById(cartid);
		if(cartdata.isEmpty())
		{
			throw new Exception_class("Cart Id not exists "+cartid);
		}
		cart cart_getdata=cartdata.get();
		
		if(cart_getdata == null)
		{
			throw new Exception_class("Cart Id not exists "+cartid);
		}
		
		return cart_getdata;
	}
	
	public cart update_Cart( int cartid,cart cartEntity)
	{
		Optional<cart> cartdata=repo.findById(cartid);
		
		if(cartdata.isEmpty())
		{
			throw new Exception_class("Cart Id not exists "+cartid);
		}
		
		cart get_cart_update=cartdata.get();
		
		if(get_cart_update==null)
		{
			throw new Exception_class("Cart Id not exists "+cartid);
		}
		
		get_cart_update.setItem(cartEntity.getItem());
		
		return repo.save(get_cart_update);
	}
	
	public cart delete_Cart(int cartid)
	{
		Optional<cart> cartdata= repo.findById(cartid);
		if(cartdata.isEmpty())
		{
			throw new Exception_class("Cart Id not exists "+cartid);
		}
		
		cart get_cartdataDelete = cartdata.get();
		if(get_cartdataDelete == null)
		{
			throw new Exception_class("Cart Id not exists "+cartid);
		}
		
		repo.deleteById(cartid);
		return get_cartdataDelete;
	}

}
