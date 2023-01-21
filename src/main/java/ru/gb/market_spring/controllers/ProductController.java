package ru.gb.market_spring.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.market_spring.converters.ProductConverter;
import ru.gb.market_spring.dto.ProductDto;
import ru.gb.market_spring.entities.Product;
import ru.gb.market_spring.exceptions.MarketError;
import ru.gb.market_spring.exceptions.ResourceNotFoundException;
import ru.gb.market_spring.services.ProductService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductConverter productConverter;

    @GetMapping
    public List<ProductDto> findAllProducts() {
        return productService.findAll().stream().map(productConverter::entityToTdo).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProductDto findProducts(@PathVariable Long id) {
        Product p = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Продукт с id: "+id+" не найден"));
//        return new ProductDto(p.getId(),p.getTitle(),p.getPrice(),p.getCategory().getTitle());
        return productConverter.entityToTdo(p);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProducts(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @PostMapping
    public ProductDto createNewProduct(@RequestBody ProductDto productDto){
        Product p = productService.createNewProduct(productDto);
        return productConverter.entityToTdo(p);
    }
}