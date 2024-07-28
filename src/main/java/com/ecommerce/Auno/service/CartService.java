package com.ecommerce.Auno.service;

import com.ecommerce.Auno.exception.ProductException;
import com.ecommerce.Auno.model.Cart;
import com.ecommerce.Auno.model.User;
import com.ecommerce.Auno.request.AddItemRequest;

public interface CartService {
	
	public Cart createCart(User user);
	
	public String addCartItem(Long userId,AddItemRequest req) throws ProductException;
	
	public Cart findUserCart(Long userId);

}
