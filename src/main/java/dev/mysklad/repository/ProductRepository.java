package dev.mysklad.repository;

import dev.mysklad.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public interface ProductRepository {

    Map<String, Product> products = new HashMap<>();

    List<Product> findAll();

    Product findById(String id);

    Product save(Product product);

    Product updateById(String id, Product product);

    void deleteById(String id);

}
