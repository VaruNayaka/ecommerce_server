package com.ecommerce.Auno.service;

import com.ecommerce.Auno.exception.CartItemException;
import com.ecommerce.Auno.exception.UserException;
import com.ecommerce.Auno.model.Cart;
import com.ecommerce.Auno.model.CartItem;
import com.ecommerce.Auno.model.Product;

public interface CartItemService {
	public CartItem createCartItem(CartItem cartitem);
	public  CartItem updateCartItem(Long userId,Long Id,CartItem cartItem) throws CartItemException,UserException;
	public CartItem isCartItemExist(Cart cart,Product product,String size,Long userId);
	public void removeCartItem(Long userId,Long cartItemId)throws CartItemException,UserException;
	public CartItem findCartItemById(Long cartItemId)throws CartItemException;
	

}
