package com.example.demo.model;

import org.springframework.web.multipart.MultipartFile;

public class ProductForm {
    private String productName;
    private String description;
    private double price;
    private String category;
 
    private boolean isAvailable = true;
 
    // Upload file.
    private MultipartFile fileData;
 
    public ProductForm() {
        this.isAvailable= true;
    }
 
   
    public ProductForm(String productName, String description, double price) {
		super();
		this.productName = productName;
		this.description = description;
		this.price = price;
		this.category = category;
	}
 
    
    public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public boolean isAvailable() {
		return isAvailable;
	}


	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}


	public double getPrice() {
        return price;
    }
 
    public void setPrice(double price) {
        this.price = price;
    }
 
    public MultipartFile getFileData() {
        return fileData;
    }
 
    public void setFileData(MultipartFile fileData) {
        this.fileData = fileData;
    }
 
    public boolean isNewProduct() {
        return isAvailable;
    }
 
    public void setNewProduct(boolean newProduct) {
        this.isAvailable = newProduct;
    }


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}
 
}