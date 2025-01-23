package dev.mysklad.web.controller;

import dev.mysklad.entity.Product;
import dev.mysklad.services.ProductService;
import dev.mysklad.web.DTO.ProductDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> findAllProducts() {
        return ResponseEntity.ok(productService.findAllProducts().stream()
                .map(this::toDTO).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findByIdProduct(@PathVariable String id) {
        return ResponseEntity.ok(toDTO(productService.findByIdProduct(id)));
    }

    @PostMapping
    public ResponseEntity<ProductDTO> saveProduct(@RequestBody ProductDTO productDTO) {
        Product product = productService.saveProduct(toEntity(productDTO));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(toDTO(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable String id, @RequestBody ProductDTO productDTO){
        Product product = productService.updateProduct(id, toEntity(productDTO));
        return ResponseEntity.ok(toDTO(product));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id){
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }


    private Product toEntity(ProductDTO productDTO) {
        return new Product(productDTO.getName(), productDTO.getDescription(), productDTO.getPrise(), productDTO.isAvailability());
    }

    private ProductDTO toDTO(Product product) {
        return new ProductDTO(product.getId(), product.getName(), product.getDescription(), product.getPrise(), product.isAvailability());
    }

}
