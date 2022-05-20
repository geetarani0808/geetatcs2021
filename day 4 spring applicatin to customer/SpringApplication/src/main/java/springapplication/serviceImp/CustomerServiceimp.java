package springapplication.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;

import springapplication.bean.Customer;
import springapplication.repository.MyLocalRepository;
import springapplication.service.CustomerService;

public class CustomerServiceimp implements CustomerService {
	
	@Autowired
	MyLocalRepository mylocalrepository;
	
	@Override
	public Customer addCustomer(Customer customer) {
		Customer customeradded = mylocalrepository.save(customer);
		
		return customeradded;
	}

}
