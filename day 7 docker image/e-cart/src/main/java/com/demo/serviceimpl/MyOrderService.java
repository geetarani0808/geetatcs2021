package com.demo.serviceimpl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.bean.Order;
import com.demo.repo.MyRepo;
import com.demo.service.OrderService;


@Service
public class MyOrderService implements OrderService {

	@Autowired
	MyRepo myRepo;

	@Override
	public Order createOder(Order order) {

		Order ord = myRepo.save(order);

		return ord;
	}

	@Override
	public List<Order> getAllOrders() {
		List<Order> orderdetails = myRepo.findAll();
		return orderdetails;
		
	}

	@Override
	public Order updateItem(Order order, Long id) {
		order.setId(id);
		Order updatedItem = myRepo.save(order);

		return updatedItem;
		
	}

	
}
