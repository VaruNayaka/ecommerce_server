package com.ecommerce.Auno.service;

import org.springframework.stereotype.Service;

import com.ecommerce.Auno.exception.ProductException;
import com.ecommerce.Auno.model.Cart;
import com.ecommerce.Auno.model.CartItem;
import com.ecommerce.Auno.model.Product;
import com.ecommerce.Auno.model.User;
import com.ecommerce.Auno.repository.CartRepository;
import com.ecommerce.Auno.request.AddItemRequest;

@Service
public class CartServiceImplementation implements CartService {
	private CartRepository cartRepository;
	private CartItemService cartitemservice;
	private ProductService productService;
	
	public CartServiceImplementation(CartRepository cartRepository, CartItemService cartitemservice,
			ProductService productService) {
		this.cartRepository = cartRepository;
		this.cartitemservice = cartitemservice;
		this.productService = productService;
	}

	@Override
	public Cart createCart(User user) {
		Cart cart=new Cart();
		cart.setUser(user);
		return cartRepository.save(cart);
	}

	@Override
	public String addCartItem(Long userId, AddItemRequest req) throws ProductException {
		Cart cart=cartRepository.findByUserId(userId);
		Product product=productService.findProductById(req.getProductId());
		CartItem isPresent=cartitemservice.isCartItemExist(cart, product, req.getSize(), userId);
		if(isPresent==null) {
			CartItem cartItem=new CartItem();
			cartItem.setProduct(product);
			cartItem.setCart(cart);
			cartItem.setQuantity(req.getQuantity());
			cartItem.setUserId(userId);
			
			int price=req.getQuantity()*product.getDiscountedprice();
			cartItem.setPrice(price);
			cartItem.setSize(req.getSize());
			
			CartItem createdCartItem=cartitemservice.createCartItem(cartItem);
			cart.getCartItems().add(createdCartItem);
		}
		
		return "Item Added";
	}

	@Override
	public Cart findUserCart(Long userId) {
		Cart cart=cartRepository.findByUserId(userId);
		int totalPrice=0;
		int totalDiscountedPrice=0;
		int totalItem=0;
		for(CartItem cartItem :cart.getCartItems()) {
			totalPrice+=cartItem.getPrice();
			totalDiscountedPrice+=cartItem.getDiscountedPrice();
			totalItem+=cartItem.getQuantity();
		}
		cart.setTotalDiscountedPrice(totalDiscountedPrice);
		cart.setTotalPrice(totalPrice);
		cart.setDiscount(totalPrice-totalDiscountedPrice);
		cart.setTotalItem(totalItem);
		return cartRepository.save(cart);
	}

}
