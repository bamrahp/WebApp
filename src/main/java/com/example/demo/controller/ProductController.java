package com.example.demo.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.ProductForm;
import com.example.demo.service.ProductService;

@Controller
public class ProductController {
	@Autowired
	private ProductService productService;
	@RequestMapping(value = "/addItem", method = RequestMethod.POST)
    public String addProduct(
    		@RequestParam("productName")String name, 
    		@RequestParam("price")Double price,
    		@RequestParam("description")String description, 
    		@RequestParam("image")MultipartFile image,
    		@RequestParam("category") String category
    		) {
		
		
		ProductForm product = new ProductForm();
		
		product.setProductName(name);
		product.setPrice(price);
		product.setDescription(description);
		product.setFileData(image);
		product.setCategory(category);
		
											 
		try {
			productService.save(product);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return "redirect:/home";
    }
}
