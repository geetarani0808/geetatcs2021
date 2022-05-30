package amazonapp.service;

import java.util.List;

import amazonapp.bean.Product;

public interface ProductService {
	
	// service give by admin insest
	Product addProduct(Product product);
	
	// get all list of products
	List<Product> getAllListOfProducts();
	
	//get by id
	
	//Product getProductById(long id);
	

	
	//put or update
	 
	Product updateProduct(Product product,long id);
	
	   
	
	
	
	
	// delete
	/// delete service
	 Void deleteProductById(long id);

}
