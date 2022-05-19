package amazonapp.serviceimp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import amazonapp.bean.Product;
import amazonapp.repositary.MyLocalRepository;
import amazonapp.service.ProductService;

/*What is @service annotation in Spring boot?
It is used to mark the class as a service provider.
 So overall @Service annotation is used with classes 
 that provide some business functionalities. 
 Spring context will autodetect these classes
  when annotation-based configuration and 
  classpath scanning is used. Procedure.  */

@Service
public class ProductServiceImp implements ProductService{
//inject the depedency MyLocalRepository in service impl class
	@Autowired
	MyLocalRepository myLocalRepository;
	// this serive of add functionality business logic
	@Override
	public Product addProduct(Product product) {
		
		Product prodcutadded = myLocalRepository.save(product);
	
		return prodcutadded;
	}

}
