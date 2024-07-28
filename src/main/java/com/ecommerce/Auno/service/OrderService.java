package com.ecommerce.Auno.service;

import java.util.List;

import com.ecommerce.Auno.exception.OrderException;
import com.ecommerce.Auno.model.Address;
import com.ecommerce.Auno.model.Order;
import com.ecommerce.Auno.model.User;

public interface OrderService {
	public Order createOrder(User user,Address shippingAddress);
	public Order findOrderById(Long orderId) throws OrderException;
	public List<Order> userOrderhistory(Long userId);
	public Order placedOrder(Long orderId) throws OrderException;
	public Order confirmedOrder(Long orderId) throws OrderException;
	public Order shippedOrder(Long orderId) throws OrderException;
	public Order deliveredOrder(Long orderId) throws OrderException;
	public Order cancledOrder(Long orderId) throws OrderException;
	public List<Order>getAllOrders();
	public void deleteOrder(Long orderId) throws OrderException;
	
	
}
