package com.example.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Cart;
import com.example.demo.model.Item;
import com.example.demo.repo.CartRepository;
import com.example.demo.repo.OrderItem;
import com.example.demo.repo.OrderRepository;

@Service
@Transactional

public class CartService {

	
	private CartRepository cartRepo;
	private OrderItem itemRepo;
	
	@Autowired
	public CartService(CartRepository cartRepo, OrderRepository orderRepo) {
		super();
		this.cartRepo = cartRepo;
		this.itemRepo = itemRepo;
	}

	public void save(Cart cart) {
		// TODO Auto-generated method stub
		cartRepo.save(cart);
			
	}

	public void remove(Item item) {
		// TODO Auto-generated method stub
		itemRepo.delete(item);
		
	}

	

	
}
