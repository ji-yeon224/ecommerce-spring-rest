package kr.ac.hansung.cse.ecommercespringrest.service;


import kr.ac.hansung.cse.ecommercespringrest.entity.Category;
import kr.ac.hansung.cse.ecommercespringrest.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    /**
     * Gets all products present in the system.
     * The result is paginated.
     *
     * @return the paginated results
     */
    List<Product> getAllProducts();

    /**
     * Gets a specific product by looking for its id.
     *
     * @param id the id of the product to look for
     * @return the product (if any)
     */
    Optional<Product> getProductById(Long id);

    /**
     * Creates a product.
     * If the currency is not 'EUR' then a Currency Exchange
     * will be performed.
     *
     * @param name the name of the product
     * @param price the price of the product
     * @return the new product
     */
    Product createProduct(String name, double price);

    /**
     * Updates an existing product.
     * If the currency is not 'EUR' then a Currency Exchange
     * will be performed.
     *
     * @param product the product to update
     * @param name the new product name
     * @param price the new product price
     */
    void updateProduct(Product product, String name,  double price);

    /**
     * Deletes a product in the system.
     *
     * @param product the product to delete
     */
    void deleteProduct(Product product);

    /**
     * Checks whether the product has a given category.
     *
     * @param product the product to check
     * @param category the category to check
     * @return true if the product is associated to the category
     */
    boolean hasCategory(Product product, Category category);

    /**
     * Adds a category to the product.
     *
     * @param product the product to add the category to
     * @param category the category to add
     */
    void addCategory(Product product, Category category);

    /**
     * Removes a category from the product.
     *
     * @param product the product to remove the category from
     * @param category the category to remove
     */
    void removeCategory(Product product, Category category);

}
