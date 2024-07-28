package com.ecommerce.Auno.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.Auno.model.OrderItem;
import com.ecommerce.Auno.repository.OrderItemRepository;

@Service
public class OrderItemServiceImplementation implements OrderItemService {
	@Autowired
	private OrderItemRepository orderItemRepository;
	




	@Override
	public OrderItem createOrderItem(OrderItem orderItem) {
		return orderItemRepository.save(orderItem);
	}

}
