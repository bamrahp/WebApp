package com.example.demo.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Cart;
import com.example.demo.model.Employee;
import com.example.demo.model.Item;
import com.example.demo.model.Product;
import com.example.demo.repo.OrderItem;
import com.example.demo.service.CartService;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.ProductService;

@Controller
public class HomeController {
	
	
	
	@Autowired
	private ProductService proService;
	@Autowired
    private EmployeeService empService;
	@Autowired
    private CartService cartService;
	@Autowired
    private OrderItem itemService;
	List<Item> items;
	List<Product> products;
	String product;
	
	@RequestMapping("/home")
    public ModelAndView home(Principal principal) {
    	//System.out.println("hey there "+principal.getName());
    	Employee user =empService.findUserByEmail(principal.getName());
    	//System.out.println(user.getRoles());
    	 products = proService.getAll();
    	ModelAndView mv = new ModelAndView();
    	mv.setViewName("home");
    	mv.addObject("user",empService.findUserByEmail(principal.getName()) );
    	mv.addObject("products", proService.getAll() );
    	mv.addObject("name2",product);
    	return mv;
    }
	
	@RequestMapping(value = "/cart", method = RequestMethod.GET)
	public ModelAndView index(Principal user) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("cart");
		Employee emp = empService.findUserByEmail(user.getName());
		Cart cart = emp.getCart();
		Set<Item> items= cart.getItems();
		mv.addObject("cart",items);
		return mv;
	}
	
	@RequestMapping(value = "/cart/buy/{name}", method = RequestMethod.GET)
    public String cart(@PathVariable("name") String name, HttpSession session,Principal user) {
		Product currentProduct = new Product() ;
		products = proService.getAll();
		for(Product product:products)
		{
			if(product.getProductName().toLowerCase().equals(name.toLowerCase()))
			{
				currentProduct = product;
			}
		}
		Employee emp = empService.findUserByEmail(user.getName());
		Cart cart = emp.getCart();
		if(cart==null) {
			cart = new Cart();
			emp.setCart(cart);
//			System.out.println("++++++++");
//			empService.save(emp);
//			System.out.println("Settting Cart");
		}
		Set<Item> items= cart.getItems();
		if(items.contains(currentProduct)) {
		for(Item item : items) {
			if(item.getProduct().getProductName()==name) {
				item.setQuantity(item.getQuantity()+1);
			}
		}}
		else {
			items.add(new Item(currentProduct,1));
		}
		cart.setItems(items);
		cart.setEmployee(emp);
		System.out.println("EMP ID -"+cart.getEmployee().getEmp_id());
		System.out.println("*****");
		cartService.save(cart);
		System.out.println("_____");
		return "redirect:/home";
	
	}
	
	
//	@RequestMapping(value = "/cart/remove/{name}", method = RequestMethod.GET)
//	public ModelAndView remove(@PathVariable("name") String name, HttpSession session,Principal user) {
//		
//		
//	}

	@RequestMapping("favicon.ico")
    @ResponseBody
    void favicon() {}
}
