package amazonapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import amazonapp.bean.Product;
import amazonapp.service.ProductService;

@RestController
public class ProductController {
	// inject the depdendency of service class into controller

	@Autowired
	ProductService productService;

	// create service for admin
	@PostMapping(value = "/createproduct") // endpoint
	Product createProduct(@RequestBody Product prod) {
		Product p = productService.addProduct(prod);// calling service from controller
		System.out.println(" product->>>>> " + p.getProductname());// print on server side
		System.out.println(" color->>>>> " + p.getColor());
		return p;

	}

	// get list of product from the db
	@GetMapping(value = "/viewalllofproduct")
	List<Product> viewAllListOfProduct() {

		return productService.getAllListOfProducts();

	}

	//
	@PutMapping(value="/updateproduct/{id}")
	Product updateProduct(@RequestBody Product updateproduct,@PathVariable long id) {

	 Product updatedDetails= productService.updateProduct(updateproduct,id);
	
		
	 return updatedDetails;

	}
  @DeleteMapping(value ="/deleteproductbyid/{productid}")
  ResponseEntity<String> deleteProductBy(@PathVariable long productid) {
	  try {
		  
		  
		   if (productid == 0) {
			   
			   return ResponseEntity.ok("pls enter in proper id" +productid);
			   
		   }
		   productService.deleteProductById(productid);
		   
		   return ResponseEntity.ok("Sucessfully deleted" +productid);
	  } catch (Exception e) {
		  return ResponseEntity.ok("product id does not exists" +productid);
	  }
  }
}
