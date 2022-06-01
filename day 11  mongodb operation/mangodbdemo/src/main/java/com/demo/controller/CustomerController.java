package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.bean.Customer;
import com.demo.service.CustomerService;

@RestController
public class CustomerController {
	@Autowired
	CustomerService CustomerService;

	@PostMapping(value="/createoreder")
	Customer addOrder(@RequestBody Customer customer) {

		Customer addcustomer = CustomerService.createcustomer(customer);
		return addcustomer;

	}

	
	@GetMapping(value = "/customerdetails")
	List<Customer> getAllCustomer() {

		return CustomerService.getAllListofCustomers();

	}
	
	@PutMapping(value="/updateitem/{id}")
	Customer updateItem(@RequestBody Customer updateitem,@PathVariable long id) {

	 Customer updatedDetails= ((CustomerController) CustomerService).updateItem(updateitem,id);
	
		
	 return updatedDetails;
}
}

