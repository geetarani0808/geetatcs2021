package springapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import springapplication.bean.Customer;
import springapplication.service.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	CustomerService customerservice;
	
	@PostMapping(value="/addcustomer")
	Customer addCustomer(@RequestBody Customer customer) {
		Customer cust = customerservice.addCustomer(customer);
		return cust;
				
	}
	
}
