package ru.gb.market_spring.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.gb.market_spring.entities.Product;
import ru.gb.market_spring.services.ProductService;

import java.util.List;


@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public List<Product> findAllProducts() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public Product findProducts(@PathVariable Long id) {
        return productService.findById(id).get();
    }

    @DeleteMapping("/{id}")
    public void deleteProducts(@PathVariable Long id) {
        productService.deleteById(id);
    }
}