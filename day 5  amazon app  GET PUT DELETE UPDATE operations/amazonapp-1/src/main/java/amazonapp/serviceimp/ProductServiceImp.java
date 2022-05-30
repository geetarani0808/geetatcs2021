package amazonapp.serviceimp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import amazonapp.bean.Product;
import amazonapp.repositary.MyLocalRepository;
import amazonapp.service.ProductService;

@Service
public class ProductServiceImp implements ProductService {

	// injected the depedency MyLocalRepository into service class
	@Autowired
	MyLocalRepository myLocalRepository;
	private CrudRepository<Product, Long> myLocalRepositary;

	public Product addProduct(Product product) {

		Product addprodcut = myLocalRepository.save(product);

		return addprodcut; // returning to controler
	}

	// added new service here
	public List<Product> getAllListOfProducts() {

		List<Product> listOfproducts = myLocalRepository.findAll();

		return listOfproducts;
	}
	// for update product by admin

	public Product updateProduct(Product product, long id) {

		product.setId(id);
		Product updatedproduct = myLocalRepository.save(product);

		return updatedproduct;
	}
 /// delete service
	@Override
	 public void deleteProductById(long id) {
		myLocalRepository.deleteById(id); //delete from table where id =?
	}
}
