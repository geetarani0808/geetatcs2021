package springapplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
		System.out.println(" customer->>>>> " + cust.getCustomername());// print on server side
		System.out.println(" branch->>>>> " + cust.getBranchname());
		System.out.println(" designation->>>>> " + cust.getDesignation());
		return cust;
				
	}
	
	@GetMapping(value = "/viewallcustomers")
	List<Customer> viewAllListOfProduct() {

		return customerservice.getAllListofCustomers();

	}
	
	@PutMapping(value="/updatecustomer/{id}")
	Customer updateCustomer(@RequestBody Customer updatecustomer,@PathVariable long id) {

	 Customer updatedDetails= customerservice.updateCustomer(updatecustomer, id);
	
		
	 return updatedDetails;
	 
	}
	
	
	
	
}
