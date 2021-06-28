package kr.ac.hansung.cse.ecommercespringrest.api.controller;

import kr.ac.hansung.cse.ecommercespringrest.api.Representationmodel.ProductModel;
import kr.ac.hansung.cse.ecommercespringrest.api.assembler.ProductModelAssembler;
import kr.ac.hansung.cse.ecommercespringrest.entity.Product;
import kr.ac.hansung.cse.ecommercespringrest.exception.NotFoundException;
import kr.ac.hansung.cse.ecommercespringrest.service.ProductService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/*
URL: /api/products

To get list of products:  GET "http://localhost:8080/api/products"
To get products info:  GET "http://localhost:8080/api/products/{id}"
To create products:   POST "http://localhost:8080/api/products" -d '{ "name": "P1", "price": 100.00 }'
To update products:   PUT "http://localhost:8080/api/products/{id}" -d '{ "name": "P1", "price": 100.00 }'
To delete products: DELETE "http://localhost:8080/api/products/{id}"
*/

@RestController
@RequestMapping(path = "/api/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductModelAssembler productModelAssembler;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> retrieveAllProducts() {
		
		// Getting all products in application...
		final List<Product> products = productService.getAllProducts();

		return ResponseEntity.ok(
				productModelAssembler.toCollectionModel(products));
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> retrieveProduct(@PathVariable Long id) {

		return productService.getProductById(id)
				.map(productModelAssembler::toModel)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	// DTO(Data Transfer Object) : 계층간 데이터 교환을 위한 객체, 여기서는 클라이언트(Postman)에서 오는 데이터를 수신할 목적으로 사용
    // Product와 ProductDto와의 차이를 비교해서 살펴보기 바람
    @RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> createProduct(@RequestBody @Valid ProductDto request) {

		Product product = productService.createProduct(request.getName(), request.getPrice());
		ProductModel productModel = productModelAssembler.toModel(product);

		return ResponseEntity.status(HttpStatus.CREATED).body(productModel);
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody @Valid ProductDto request) {

		// Getting the requiring product; or throwing exception if not found
		final Product product = productService.getProductById(id)
				.orElseThrow(() -> new NotFoundException(id));

		// Updating a product in the application...
		productService.updateProduct(product, request.getName(), request.getPrice());

		return ResponseEntity.ok(productModelAssembler.toModel(product));
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {

		// Getting the requiring product; or throwing exception if not found
		final Product product = productService.getProductById(id)
				.orElseThrow(() -> new NotFoundException(id));

		// Deleting product from the application...
		productService.deleteProduct(product);

		return ResponseEntity.noContent().build();
	}
	
	@Getter
	@Setter
	static class ProductDto {
        @NotNull(message = "name is required")
        @Size(message = "name must be equal to or lower than 300", min = 1, max = 300)
        private String name;           
        
        @NotNull(message = "name is required")
        @Min(0)
        private Double price;
	}
}