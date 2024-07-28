package com.ecommerce.Auno.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.Auno.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

}
