package com.ecommerce.Auno.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ecommerce.Auno.model.Cart;
import com.ecommerce.Auno.model.CartItem;
import com.ecommerce.Auno.model.Product;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
	@Query("SELECT ci From CartItem ci  where ci.cart=:cart And ci.product=:product And ci.size=:size And ci.userId=:userId")
	public CartItem isCartItemExist(@Param("cart") Cart cart,
			@Param("product") Product product,
			@Param("size") String size, 
			@Param("userId") Long userId);

}
