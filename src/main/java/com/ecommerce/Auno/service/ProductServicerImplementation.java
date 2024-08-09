package com.ecommerce.Auno.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ecommerce.Auno.exception.ProductException;
import com.ecommerce.Auno.model.Category;
import com.ecommerce.Auno.model.Product;
import com.ecommerce.Auno.repository.CategoryRepository;
import com.ecommerce.Auno.repository.ProductRepository;
import com.ecommerce.Auno.request.CreateProductRequest;

@Service
public class ProductServicerImplementation implements ProductService {
	private ProductRepository productRepository;
	private UserService userService;
	private CategoryRepository categoryRepository;

	public ProductServicerImplementation(ProductRepository productRepository, UserService userService,
			CategoryRepository categoryRepository) {
		this.productRepository = productRepository;
		this.userService = userService;
		this.categoryRepository = categoryRepository;
	}

	@Override
public Product createProduct(CreateProductRequest req) {
		
		Category topLevel=categoryRepository.findByName(req.getTopLavelCategory());
		
		if(topLevel==null) {
			
			Category topLavelCategory=new Category();
			topLavelCategory.setName(req.getTopLavelCategory());
			topLavelCategory.setLevel(1);
			
			topLevel= categoryRepository.save(topLavelCategory);
		}
		
		Category secondLevel=categoryRepository.
				findByNameAndParant(req.getSecondLavelCategory(),topLevel.getName());
		if(secondLevel==null) {
			
			Category secondLavelCategory=new Category();
			secondLavelCategory.setName(req.getSecondLavelCategory());
			secondLavelCategory.setParentCategory(topLevel);
			secondLavelCategory.setLevel(2);
			
			secondLevel= categoryRepository.save(secondLavelCategory);
		}

		Category thirdLevel=categoryRepository.findByNameAndParant(req.getThirdLavelCategory(),secondLevel.getName());
		if(thirdLevel==null) {
			
			Category thirdLavelCategory=new Category();
			thirdLavelCategory.setName(req.getThirdLavelCategory());
			thirdLavelCategory.setParentCategory(secondLevel);
			thirdLavelCategory.setLevel(3);
			
			thirdLevel=categoryRepository.save(thirdLavelCategory);
		}
		
		
		Product product=new Product();
		product.setTitle(req.getTitle());
		product.setColor(req.getColor());
		product.setDescription(req.getDescription());
		product.setDiscountedprice(req.getDiscountedPrice());
		product.setDiscountPersent(req.getDiscountPersent());
		product.setImageUrl(req.getImageUrl());
		product.setBrand(req.getBrand());
		product.setPrice(req.getPrice());
		product.setSizes(req.getSizes());
		product.setQuantity(req.getQuantity());
		product.setCategory(thirdLevel);
		product.setCreatedAt(LocalDateTime.now());
		
		Product savedProduct= productRepository.save(product);
		
		System.out.println("products - "+product);
		
		return savedProduct;
	}


	@Override
	public String deleteProduct(Long productid) throws ProductException {
		Product product = findProductById(productid);
		product.getSizes().clear();
		productRepository.delete(product);

		return "Product Deleted Successfully";
	}

	@Override
	public Product UpdateProduct(Long productid, Product req) throws ProductException {
		Product product = findProductById(productid);
		if (req.getQuantity() != 0) {
			product.setQuantity(req.getQuantity());
		}
		return productRepository.save(product);
	}

	@Override
	public Product findProductById(Long id) throws ProductException {
		Optional<Product> opt = productRepository.findById(id);
		if (opt.isPresent()) {
			return opt.get();
		}
		throw new ProductException("Product Not Found With id" + id);
	}

	@Override
	public List<Product> findProductByCategory(String Category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Product> getAllProduct(String Category, List<String> colors, List<String> sizes, Integer minPrice,
			Integer maxPrice, Integer minDiscount, String sort, String stock, Integer pageNumber, Integer pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		List<Product> products = productRepository.filterProducts(Category, minPrice, maxPrice, minDiscount, sort);
		if (!colors.isEmpty()) {
			products = products.stream().filter(p -> colors.stream().anyMatch(c -> c.equalsIgnoreCase(p.getColor())))
					.collect(Collectors.toList());
		}

		if (stock != null && stock.equalsIgnoreCase("in_stock")) {
			products = products.stream().filter(p -> p.getQuantity() > 0).collect(Collectors.toList());
		} else if (stock.equalsIgnoreCase("out_of_stock")) {
			products = products.stream().filter(p -> p.getQuantity() < 1).collect(Collectors.toList());
		}

		int startIndex = (int) pageable.getOffset();
		int endIndex = Math.min(startIndex + pageable.getPageSize(), products.size());

		List<Product> pageContents = products.subList(startIndex, endIndex);
		Page<Product> filterProducts = new PageImpl<>(pageContents, pageable, products.size());
		return filterProducts;
	}
	@Override
	public List<Product> searchProduct(String query) {
		List<Product> products=productRepository.searchProduct(query);
		return products;
	}


	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}


	}
