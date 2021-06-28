package kr.ac.hansung.cse.ecommercespringrest.service;

import kr.ac.hansung.cse.ecommercespringrest.entity.Category;
import kr.ac.hansung.cse.ecommercespringrest.entity.Product;
import kr.ac.hansung.cse.ecommercespringrest.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product createProduct(String name, double price) {

        // Round up only 2 decimals...
        price = (double) Math.round(price * 100) / 100;

        Product product = new Product();
        product.setName(name);
        product.setPrice(price);

        return productRepository.save(product);
    }

    @Override
    public void updateProduct(Product product, String name,  double price) {

        // Round up only 2 decimals...
        price = (double) Math.round(price * 100) / 100;

        product.setName(name);
        product.setPrice(price);
        productRepository.save(product);
    }

    @Override
    public void deleteProduct(Product product) {
        productRepository.delete(product);
    }

    @Override
    public boolean hasCategory(Product product, Category category) {
        return product.getCategories().contains(category);
    }

   @Override
    public void addCategory(Product product, Category category) {
        product.getCategories().add(category);
        productRepository.save(product);
    }

    @Override
    public void removeCategory(Product product, Category category) {
        product.getCategories().remove(category);
        productRepository.save(product);
    }
}