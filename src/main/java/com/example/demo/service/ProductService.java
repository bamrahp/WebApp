package com.example.demo.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Product;
import com.example.demo.model.ProductForm;
import com.example.demo.repo.ProductRepository;

@Service
@Transactional
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public List<Product> getAll() {
		// TODO Auto-generated method stub
		return productRepository.findAll();
		
	}

	public void save(ProductForm product) throws IOException {
		// TODO Auto-generated method stub
		Product p = new Product();
		p.setDescription(product.getDescription());
		p.setProductName(product.getProductName());
		p.setPrice(product.getPrice());
		p.setCategory(product.getCategory());
		MultipartFile  file= product.getFileData();
		byte [] byteArr=file.getBytes();
		//InputStream inputStream = new ByteArrayInputStream(byteArr);
		p.setImage(byteArr);
	
//		MultipartFile file = product.getImage();
//	    InputStream iStream = file.getInputStream();
//	    long size = file.getSize();
//	    EntityManager em = emf.createEntityManager();
//	    Session session = (Session) em.getDelegate();
//	    Blob cv = Hibernate.getLobCreator(session).createBlob(iStream, size);
		
		productRepository.save(p);
		
	}

	public Product findByProductName(String name) {
		// TODO Auto-generated method stub
		return productRepository.findByProductName(name);
	}
	
	
}
