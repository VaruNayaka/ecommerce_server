package com.ecommerce.Auno.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.Auno.exception.ProductException;
import com.ecommerce.Auno.model.Product;
import com.ecommerce.Auno.model.Review;
import com.ecommerce.Auno.model.User;
import com.ecommerce.Auno.repository.ProductRepository;
import com.ecommerce.Auno.repository.ReviewRepository;
import com.ecommerce.Auno.request.ReviewRequest;

@Service
public class ReviewServiceImplementation implements ReviewService {
	private ReviewRepository reviewRepository;
	private ProductService productService;
	private ProductRepository productRepository;

	public ReviewServiceImplementation(ReviewRepository reviewRepository, ProductService productService,
			ProductRepository productRepository) {
		this.reviewRepository = reviewRepository;
		this.productService = productService;
		this.productRepository = productRepository;
	}

	@Override
	public Review createReview(ReviewRequest req, User user) throws ProductException {
		Product product = productService.findProductById(req.getProductId());
		Review review = new Review();
		review.setUser(user);
		review.setProduct(product);
		review.setReview(req.getReview());
		review.setCreatedAt(LocalDateTime.now());
		
		productRepository.save(product);
		return reviewRepository.save(review);

	}

	@Override
	public List<Review> getAllReview(Long productId) {
		
		return reviewRepository.getAllProductReview(productId);
	}

}
