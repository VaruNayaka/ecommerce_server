package com.ecommerce.Auno.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.ecommerce.Auno.exception.ProductException;
import com.ecommerce.Auno.model.Product;
import com.ecommerce.Auno.request.CreateProductRequest;

public interface ProductService {
	
	public Product createProduct(CreateProductRequest req) ;
	public String deleteProduct(Long productid) throws ProductException;
	public Product UpdateProduct(Long productid,Product req) throws ProductException;
	public Product findProductById(Long id)throws ProductException;
	public List<Product>findProductByCategory(String Category);
	public Page<Product>getAllProduct(String Category,List<String> colors,List<String>sizes,Integer minPrice,Integer maxPrice,
			Integer minDiscount,String sort,String stock,Integer pageNumber,Integer pageSize);
	public List<Product> getAllProducts() ;

}
