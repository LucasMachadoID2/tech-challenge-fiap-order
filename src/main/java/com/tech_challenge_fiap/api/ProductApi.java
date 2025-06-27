package com.tech_challenge_fiap.api;

import com.tech_challenge_fiap.controller.product.ProductController;
import com.tech_challenge_fiap.dtos.ProductRequestDto;
import com.tech_challenge_fiap.dtos.ProductResponseDto;
import com.tech_challenge_fiap.utils.enums.CategoryEnum;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Product", description = "Operations related to product management")
@RestController
@RequestMapping("/v1/products")
@RequiredArgsConstructor
public class ProductApi {

    private final ProductController productController;

    @PostMapping
    public ResponseEntity<ProductResponseDto> create(@RequestBody ProductRequestDto request) {
        var created = productController.create(request);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> update(@PathVariable String id, @RequestBody ProductRequestDto request) {
        var updated = productController.update(id, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        productController.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> list() {
        var products = productController.findAll();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/category")
    public ResponseEntity<List<ProductResponseDto>> findByCategory(
            @RequestParam(value = "category") CategoryEnum category) {
        var products = productController.findByCategory(category);
        return ResponseEntity.ok(products);
    }
}
