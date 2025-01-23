package dev.mysklad.mysklad.controller;

import dev.mysklad.controller.ProductController;
import dev.mysklad.entity.Product;
import dev.mysklad.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public  class ProductControllerTest {

    private final ProductService productService = new InMemoryProductService();
    private final ProductController productController = new ProductController(productService);
    ;

    @Test
    void findAll() {
        Product product1 = new Product();
        product1.setId(UUID.randomUUID().toString());
        product1.setName("Test Product");
        productService.save(product1);

        Product product2 = new Product();
        product2.setId(UUID.randomUUID().toString());
        product2.setName("Test Product");
        productService.save(product2);

        ResponseEntity<List<Product>> response = productController.findAll();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
    }

    @Test
    void findById() {
        Product product = new Product();
        product.setId(UUID.randomUUID().toString());
        product.setName("Test Product");
        productService.save(product);

        ResponseEntity<Product> response = productController.findBiId(product.getId());

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(product, response.getBody());
    }

    @Test
    void save() {
        Product product = new Product();
        product.setId(UUID.randomUUID().toString());
        product.setName("Test Product");

        ResponseEntity<Product> response = productController.save(product);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(product, response.getBody());
    }

    @Test
    void update() {
        Product product = new Product();
        product.setId(UUID.randomUUID().toString());
        product.setName("Test Product");
        productService.save(product);

        Product updatedProduct = new Product();
        updatedProduct.setId(UUID.randomUUID().toString());
        updatedProduct.setName("Updated Product");

        ResponseEntity<Product> response = productController.update(product.getId(), updatedProduct);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(updatedProduct.getName(), response.getBody().getName());
    }

    @Test
    void delete() {
        Product product = new Product();
        product.setId(UUID.randomUUID().toString());
        product.setName("Test Product");
        productService.save(product);


        ResponseEntity<Void> response = productController.delete(product.getId());

        assertEquals(204, response.getStatusCodeValue());
        assertThrows(IllegalArgumentException.class, () -> productService.findById(product.getId()));
    }

    private static class InMemoryProductService extends ProductService {
        private final List<Product> products = new ArrayList<>();

        @Override
        public List<Product> findAll() {
            return new ArrayList<>(products);
        }

        @Override
        public Product findById(String id) {
            return products.stream()
                    .filter(product -> product.getId().equals(id))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Product not found"));
        }

        @Override
        public Product save(Product product) {
            products.add(product);
            return product;
        }

        @Override
        public Product updateById(String id, Product product) {
            Product existingProduct = findById(id);
            existingProduct.setName(product.getName());
            return existingProduct;
        }

        @Override
        public void deleteById(String id) {
            Product product = findById(id);
            products.remove(product);
        }
    }
}