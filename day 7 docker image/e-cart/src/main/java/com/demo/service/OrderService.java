package com.demo.service;

import java.util.List;

import com.demo.bean.Order;

public interface OrderService {
	
	///mention user defined service
	
	Order createOder(Order order);
	
	List<Order> getAllOrders();
	
	Order updateItem(Order order, Long id);

}
