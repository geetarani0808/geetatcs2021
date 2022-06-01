package com.demo.serviceimp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.bean.Customer;
import com.demo.repositary.CustomerRepositary;
import com.demo.service.CustomerService;

@Service
public abstract class CustomerServiceImp implements CustomerService {

	private static final Object customer = null;
	@Autowired
	CustomerRepositary customerrepositary;
	
	@Override
	public    Customer  addCustomer (Customer customer) {
		Customer customeradded = customerrepositary.save(customer);
		
		return customeradded;
	}
	
	@Override
	public List <Customer> getAllListofCustomers() {
		List <Customer> listofcustomers= customerrepositary.findAll();
		 return listofcustomers;
	}
	@Override
	public  Customer updateCustomer (Customer customer, long id) {
		
	customer.setId(id);
	
	Customer updatedcustomer = customerrepositary.save(customer);
	
	 return  updatedcustomer;
	
	
}
}
