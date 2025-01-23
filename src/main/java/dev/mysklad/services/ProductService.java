package dev.mysklad.services;

import dev.mysklad.entity.Product;
import dev.mysklad.exception.DataIsIncorrect;
import dev.mysklad.exception.ProductNotFound;
import dev.mysklad.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService implements ProductRepository {

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    @Override
    public Product findById(String id) {
        Product product = products.get(id);
        if(product == null){
            throw new ProductNotFound("Продукт с ID: " + id + " не найден!");
        }
        return product;
    }

    @Override
    public Product save(Product newProduct) {
        Product product = new Product(newProduct.getName(), newProduct.getDescription(),newProduct.getPrise(),newProduct.isAvailability());
        product.setId();
        products.put(product.getId(), product);
        return product;
    }

    @Override
    public Product updateById(String id, Product updateProduct) {
        if(updateProduct == null){
            throw new DataIsIncorrect("Введите корректные данные");
        }
        Product product = findById(id);
        product = new Product(product.getId(),updateProduct.getName(),updateProduct.getDescription(),updateProduct.getPrise(),updateProduct.isAvailability());
        products.put(product.getId(),product);
        return product;
    }

    @Override
    public void deleteById(String id) {
        products.remove(id);
    }
}
