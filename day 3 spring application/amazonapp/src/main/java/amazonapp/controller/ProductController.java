package amazonapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import amazonapp.bean.Product;
import amazonapp.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	ProductService productService;

	@PostMapping(value = "/addproduct")
	Product addProduct(@RequestBody Product product) {

		Product prod = productService.addProduct(product);

		return prod;

	}

}
