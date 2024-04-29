package controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dao.IProduct;
import model.Product;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	@Autowired
	IProduct product;

	@GetMapping("/")
	public List<Product> viewProducts() {
		return product.findAll();
	}

	@DeleteMapping("/{productId}")
	public String deleteProduct(@PathVariable("productId") int productId) {
		product.deleteById(productId);
		return "Product Deleted";
	}

	@PutMapping("/{productId}")
	public String updateProduct(@RequestBody Product p, @PathVariable("productId") int productId) {
		product.findById(p.getProductId()).map(update -> {
			update.setProductName(p.getProductName());
			update.setProductPrice(p.getProductPrice());
			return product.save(update);
		});
		return "Product Updated";
	}

	@PostMapping
	public String addProduct(@RequestBody Product p) {
		product.save(p);
		return "Product Added";
	}

	@GetMapping("/{productId}")
	public Optional<Product> findProductById(@PathVariable("productId") int productId) {
		return product.findById(productId);
	}

	@GetMapping
	public Page<Product> getAllProducts(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "100") int size) {
		Pageable pageable = PageRequest.of(page, size);
		return product.findAll(pageable);
	}
}
