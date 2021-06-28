package kr.ac.hansung.cse.ecommercespringrest.api.controller;

import kr.ac.hansung.cse.ecommercespringrest.api.Representationmodel.ProductModel;
import kr.ac.hansung.cse.ecommercespringrest.api.assembler.CategoryModelAssembler;
import kr.ac.hansung.cse.ecommercespringrest.api.assembler.ProductModelAssembler;
import kr.ac.hansung.cse.ecommercespringrest.entity.Category;
import kr.ac.hansung.cse.ecommercespringrest.entity.Product;
import kr.ac.hansung.cse.ecommercespringrest.exception.NotFoundException;
import kr.ac.hansung.cse.ecommercespringrest.service.CategoryService;
import kr.ac.hansung.cse.ecommercespringrest.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/* API Endpoint for categories and products association
 *
 * Link / Unlink products
 * 
 * To see the current products for a given category, you can do a GET on
 * 		/api/categories/{categoryid}/products
 * 
 * To link / unlink products with categories you can use the following URL:
 * 		/api/categories/{categoryid}/products/{productid}
 */

@RestController
@RequestMapping(path = "/api/categories/{categoryid}/products")
public class CategoryProductsController {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductModelAssembler productModelAssembler;

	@Autowired
	private CategoryModelAssembler categoryModelAssembler;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> retrieveAllProducts(@PathVariable Long categoryid) {

		// Getting the requiring category; or throwing exception if not found
		final Category category = categoryService.getCategoryById(categoryid)
				.orElseThrow(() -> new NotFoundException(categoryid));

		// remove information about parent and child categories
		category.setParent(null);
		category.setChildCategories(null);

		return ResponseEntity.ok(categoryModelAssembler.toModel(category));
	}
	
	@RequestMapping(path = "/{productid}", method = RequestMethod.POST)
	public ResponseEntity<?> addProduct(@PathVariable Long categoryid, @PathVariable Long productid) {

		// Getting the requiring category; or throwing exception if not found
		final Category category = categoryService.getCategoryById(categoryid)
				.orElseThrow(() -> new NotFoundException(categoryid));

		// Getting the requiring product; or throwing exception if not found
		final Product product = productService.getProductById(productid)
						.orElseThrow(() -> new NotFoundException(categoryid));

		// Validating if association does not exist...
		if (product.getCategories() != null &&
				productService.hasCategory(product, category)) {
			throw new IllegalArgumentException(
					"product " + product.getId() + " already contains category " + category.getId());
		}

		// Associating product with category...
		productService.addCategory(product, category);

		ProductModel productModel  = productModelAssembler.toModel(product);

		return ResponseEntity.status(HttpStatus.CREATED).body(productModel);
	}


	@RequestMapping(path = "/{productid}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteProduct(@PathVariable Long categoryid, @PathVariable Long productid){

		final Category category = categoryService.getCategoryById(categoryid)
				.orElseThrow(() -> new NotFoundException(categoryid));

		final Product product = productService.getProductById(productid)
				.orElseThrow(() -> new NotFoundException(categoryid));
		if (product.getCategories() != null &&
				productService.hasCategory(product, category)){
			productService.removeCategory(product, category);
		}

		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

	}

}