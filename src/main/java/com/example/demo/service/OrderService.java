package com.example.demo.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Order;
import com.example.demo.repo.OrderRepository;

@Service
@Transactional
public class OrderService {
private OrderRepository orderRepo;
private EntityManager entityManager;
@Autowired
public OrderService(OrderRepository orderRepo) {
	this.entityManager = entityManager;
	this.orderRepo = orderRepo;
}

public List<Order> getOrders() {
	// TODO Auto-generated method stub
	return orderRepo.findAll();
}

public void save(Order obj) {
	// TODO Auto-generated method stub
		orderRepo.save(obj);
}


}
