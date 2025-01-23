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
public class ProductService {

    private final ProductRepository productRepository;

}
