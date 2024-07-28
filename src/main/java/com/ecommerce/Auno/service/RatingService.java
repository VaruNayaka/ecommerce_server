package com.ecommerce.Auno.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.Auno.exception.ProductException;
import com.ecommerce.Auno.model.Rating;
import com.ecommerce.Auno.model.User;
import com.ecommerce.Auno.request.RatingRequest;

@Service
public interface RatingService  {
	public Rating createRating(RatingRequest req,User user) throws ProductException;
	public List<Rating>getProductsRating(Long productId);
	
	
	

}
