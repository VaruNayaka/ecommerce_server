package com.ecommerce.Auno.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ecommerce.Auno.exception.OrderException;
import com.ecommerce.Auno.model.Address;
import com.ecommerce.Auno.model.Cart;
import com.ecommerce.Auno.model.CartItem;
import com.ecommerce.Auno.model.Order;
import com.ecommerce.Auno.model.OrderItem;
import com.ecommerce.Auno.model.User;
import com.ecommerce.Auno.repository.AddressRepository;
import com.ecommerce.Auno.repository.CartRepository;
import com.ecommerce.Auno.repository.OrderItemRepository;
import com.ecommerce.Auno.repository.OrderRepository;
import com.ecommerce.Auno.repository.UserRepository;

@Service
public class OrderServiceImplementation implements OrderService {
	private CartRepository cartRepository;
	private CartItemService cartitemService;
	private UserRepository userRepository;
	private OrderItemService orderItemService;
	private OrderItemRepository orderItemRepository;
	private AddressRepository addressRepository;
	private CartService cartService;
	private OrderRepository orderRepository;
	
	

	public OrderServiceImplementation(CartRepository cartRepository, CartItemService cartitemService,
			UserRepository userRepository, OrderItemService orderItemService, OrderItemRepository orderItemRepository,
			AddressRepository addressRepository,CartService cartService,OrderRepository orderRepository) {
		super();
		this.cartRepository = cartRepository;
		this.cartitemService = cartitemService;
		this.userRepository = userRepository;
		this.orderItemService = orderItemService;
		this.orderItemRepository = orderItemRepository;
		this.addressRepository = addressRepository;
		this.cartService=cartService;
		this.orderRepository=orderRepository;
	}

	@Override
	public Order createOrder(User user, Address shippingAddress) {
		shippingAddress.setUser(user);
		Address address=addressRepository.save(shippingAddress);
		user.getAddresses().add(address);
		userRepository.save(user);
		Cart cart=cartService.findUserCart(user.getId());
		List<OrderItem>orderItems=new ArrayList<>();
		for(CartItem item:cart.getCartItems()) {
			OrderItem orderItem=new OrderItem();
			orderItem.setPrice(item.getPrice());
			orderItem.setProduct(item.getProduct());
			orderItem.setQuantity(item.getQuantity());
			orderItem.setSize(item.getSize());
			orderItem.setUserId(item.getUserId());
			orderItem.setDiscountedPrice(item.getDiscountedPrice());
			
			OrderItem createdOrderItem=orderItemRepository.save(orderItem);
			orderItems.add(createdOrderItem);
			
		}
		
		Order createdOrder=new Order();
		createdOrder.setUser(user);
		createdOrder.setOrderItems(orderItems);
		createdOrder.setTotalPrice(cart.getTotalPrice());
		createdOrder.setTotalDiscountedPrice(cart.getTotalDiscountedPrice());
		createdOrder.setDiscount(cart.getDiscount());
		createdOrder.setTotalItems(cart.getTotalItem());
		createdOrder.setShippingAddress(address);
		createdOrder.setOrderDate(LocalDateTime.now());
		createdOrder.setOrderStatus("PENDING");
		createdOrder.getPaymentDetails().setPaymentStatus("PENDING");
		createdOrder.setCreatedAt(LocalDateTime.now());
		
		Order savedOrder=orderRepository.save(createdOrder);
		
		for(OrderItem item:orderItems) {
			item.setOrder(savedOrder);
			orderItemRepository.save(item);
		}
		return savedOrder;
	}

	@Override
	public Order findOrderById(Long orderId) throws OrderException {
		Optional<Order> opt=orderRepository.findById(orderId);
		if(opt.isPresent()) {
			return opt.get();
		}
		
		throw new OrderException("order not Exist with id"+orderId);
	}

	@Override
	public List<Order> userOrderhistory(Long userId) {
		List<Order>orders=orderRepository.getUserOrders(userId);
		return orders;
	}

	@Override
	public Order placedOrder(Long orderId) throws OrderException {
		Order order=findOrderById(orderId);
		order.setOrderStatus("PLACED");
		order.getPaymentDetails().setPaymentStatus("COMPLETED");
		return order;
	}

	@Override
	public Order confirmedOrder(Long orderId) throws OrderException {
		Order order=findOrderById(orderId);
		order.setOrderStatus("CONFIRMED");
		return orderRepository.save(order);
	}

	@Override
	public Order shippedOrder(Long orderId) throws OrderException {
		Order order=findOrderById(orderId);
		order.setOrderStatus("SHIPPED");
		
		return orderRepository.save(order);
	}

	@Override
	public Order deliveredOrder(Long orderId) throws OrderException {
		Order order=findOrderById(orderId);
		order.setOrderStatus("DELIVERD");
		
		return orderRepository.save(order);
	}

	@Override
	public Order cancledOrder(Long orderId) throws OrderException {
		Order order=findOrderById(orderId);
		order.setOrderStatus("CANCELLED");
		return orderRepository.save(order);
	}

	@Override
	public List<Order> getAllOrders() {
		
		return orderRepository.findAll();
	}

	@Override
	public void deleteOrder(Long orderId) throws OrderException {
	Order  order=findOrderById(orderId);
	orderRepository.deleteById(orderId);
	
		
	}

}
