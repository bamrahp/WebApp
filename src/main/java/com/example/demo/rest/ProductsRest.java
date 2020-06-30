package com.example.demo.rest;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Product;
import com.example.demo.model.ProductForm;
import com.example.demo.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductsRest {
	
	@Autowired 
	private ProductService productService;
	
	@GetMapping("")
	public List<Product> showAll(){
		
		return productService.getAll();
	}
	
	@PostMapping("")
	public void saveProduct(ProductForm product) throws IOException {
		System.out.println("Printing objetc"+product);
		productService.save( product);
	}
}
