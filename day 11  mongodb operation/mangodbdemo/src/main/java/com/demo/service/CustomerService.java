package com.demo.service;



import java.util.List;

import com.demo.bean.Customer;

public interface CustomerService {

	Customer addCustomer (Customer customer);
	
	List<Customer> getAllListofCustomers();

	Customer updateCustomer (Customer customer, long id);

	Customer createcustomer(Customer customer);
}
