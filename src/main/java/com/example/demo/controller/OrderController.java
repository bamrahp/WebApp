package com.example.demo.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Employee;
import com.example.demo.model.Item;
import com.example.demo.model.Order;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.OrderService;

@RestController
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private EmployeeService empService;
	@GetMapping("/orders")
	public void listOrders() {
		List<Order> orders = orderService.getOrders();
	}
	
	@PostMapping("/save")
	public void postOrders(Principal user, HttpSession session) {
		List<Item> list = new ArrayList();
		List<Item> cart = (List<Item>) session.getAttribute("cart");
		Employee emp = empService.findUserByEmail(user.getName());
		Order obj = new Order();
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();
		obj.setOrderDate(date);
		obj.setCartItems(cart);
		obj.setEmployee(emp);
		orderService.save(obj);
	}
}
