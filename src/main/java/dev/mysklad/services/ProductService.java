package dev.mysklad.services;

import dev.mysklad.entity.Product;
import dev.mysklad.exception.DataIsIncorrect;
import dev.mysklad.exception.ProductNotFound;
import dev.mysklad.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public Product findByIdProduct(String id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFound("Продукт с ID: " + id + " не найден"));
    }


    public Product saveProduct(Product product) {
        if (product == null) {
            throw new DataIsIncorrect("Введите корректные данные");
        }
        return productRepository.save(product);
    }


    public Product updateProduct(String id, Product updateProduct){
        return productRepository.findById(id).isPresent() ? saveProduct(updateProduct) : productRepository.save(updateProduct);
    }

    public void deleteProduct(String id){
        productRepository.deleteById(id);
    }


}
