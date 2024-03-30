package com.example.cartservice.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cartservice.entity.cart;
import com.example.cartservice.service.cartService;

@RestController
public class cartController {

	@Autowired
	cartService src;
	
	
	@GetMapping("/api/carts")
	public List<cart> getallCarts()
	{
		return src.getall_Carts();
	}
	
	@PostMapping("/api/carts")
	public ResponseEntity<cart> createCart(@RequestBody cart cartData)
	{
		return src.create_Cart(cartData);
	}
	
	@GetMapping("/api/carts/{cartid}")
	public cart getCart(@PathVariable int cartid)
	{
		return src.get_Cart(cartid);
	}
	
	@PutMapping("/api/carts/{cartid}")
	public cart updateCart( @PathVariable int cartid,@RequestBody cart cartData )
	{
		cart prodcart=src.update_Cart(cartid,cartData);
		return prodcart;
	}
	
	@DeleteMapping("/api/carts/{cartid}")
	public cart deleteCart(@PathVariable int cartid)
	{
		cart deleteCart=src.delete_Cart(cartid);
		return deleteCart;
	}
}
