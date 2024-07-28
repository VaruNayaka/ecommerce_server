package com.ecommerce.Auno.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ecommerce.Auno.exception.CartItemException;
import com.ecommerce.Auno.exception.UserException;
import com.ecommerce.Auno.model.Cart;
import com.ecommerce.Auno.model.CartItem;
import com.ecommerce.Auno.model.Product;
import com.ecommerce.Auno.model.User;
import com.ecommerce.Auno.repository.CartItemRepository;
import com.ecommerce.Auno.repository.CartRepository;
@Service
public class CartItemServiceImplementation implements CartItemService {
	
	private CartItemRepository cartItemRepository;
	private UserService userService;
	private CartRepository cartRepository;
	
	

	public CartItemServiceImplementation(CartItemRepository cartItemRepository, UserService userService,
			CartRepository cartRepository) {
		this.cartItemRepository = cartItemRepository;
		this.userService = userService;
		this.cartRepository = cartRepository;
	}

	@Override
	public CartItem createCartItem(CartItem cartitem) {
		cartitem.setQuantity(1);
		cartitem.setPrice(cartitem.getProduct().getPrice()*cartitem.getQuantity());
		cartitem.setDiscountedPrice(cartitem.getProduct().getDiscountedprice()*cartitem.getQuantity());
		
		CartItem createdCartItem=cartItemRepository.save(cartitem);
		
		return createdCartItem;
	}

	@Override
	public CartItem updateCartItem(Long userId, Long Id, CartItem cartItem) throws CartItemException, UserException {
	CartItem item=findCartItemById(Id);
	User user=userService.findUserById(item.getUserId());
	if(user.getId().equals(userId)) {
		item.setQuantity(cartItem.getQuantity());
		item.setPrice(item.getQuantity()*item.getProduct().getPrice());
		item.setDiscountedPrice(item.getProduct().getDiscountedprice()*item.getQuantity());	
	}
	return cartItemRepository.save(item);
		
	}

	@Override
	public CartItem isCartItemExist(Cart cart, Product product, String size, Long userId) {
		CartItem cartItem=cartItemRepository.isCartItemExist(cart, product, size, userId);
		
		return cartItem;
	}

	@Override
	public void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException {
		CartItem cartItem=findCartItemById(cartItemId);
		User user=userService.findUserById(cartItem.getUserId());
		User requser=userService.findUserById(userId);
		if(user.getId().equals(requser.getId())) {
			cartItemRepository.deleteById(cartItemId);
		}else {
			throw new UserException("Item Not Found");
		}
		
	}

	@Override
	public CartItem findCartItemById(Long cartItemId) throws CartItemException {
		Optional<CartItem>opt=cartItemRepository.findById(cartItemId);
		if(opt.isPresent()) {
			return opt.get();
		}
		throw new CartItemException("CartItem Not Found with id :"+cartItemId);
	}

}
