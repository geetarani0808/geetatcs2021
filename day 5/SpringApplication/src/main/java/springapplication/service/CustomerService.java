package springapplication.service;

import java.util.List;
import springapplication.bean.Customer;

public interface CustomerService {

	Customer addCustomer(Customer customer);
	
	List<Customer> getAllListofCustomers();
	
	Customer updateCustomer(Customer customer, long id);
	
	
}
