package com.ecommerce.Auno.service;

import java.util.List;

import com.ecommerce.Auno.exception.ProductException;
import com.ecommerce.Auno.model.Review;
import com.ecommerce.Auno.model.User;
import com.ecommerce.Auno.request.ReviewRequest;

public interface ReviewService {
	public Review createReview(ReviewRequest req,User user)throws ProductException;
	public List<Review>getAllReview(Long productId);

}
