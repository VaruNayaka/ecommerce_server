package com.ecommerce.Auno.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.Auno.exception.ProductException;
import com.ecommerce.Auno.model.Product;
import com.ecommerce.Auno.model.Rating;
import com.ecommerce.Auno.model.User;
import com.ecommerce.Auno.repository.RatingRepository;
import com.ecommerce.Auno.request.RatingRequest;

@Service
public class RatingServiceImplemtation implements RatingService {
	private RatingRepository ratingRepository;
	private ProductService productService;

	public RatingServiceImplemtation(RatingRepository ratingRepository, ProductService productService) {
		this.ratingRepository = ratingRepository;
		this.productService = productService;
	}

	@Override
	public Rating createRating(RatingRequest req, User user) throws ProductException {
		Product product = productService.findProductById(req.getProductId());
		Rating rating = new Rating();
		rating.setProduct(product);
		rating.setUser(user);
		rating.setRating(req.getRating());
		rating.setCreatedAt(LocalDateTime.now());
		
		return ratingRepository.save(rating);
	}

	@Override
	public List<Rating> getProductsRating(Long productId) {
		
		return ratingRepository.getAllProductRatings(productId);
	}

}
