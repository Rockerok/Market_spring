package ru.gb.market_spring.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping
    public List<ProductDto> findAllProducts() {
        return productService.findAll().stream().map(p -> new ProductDto(p.getId(),p.getTitle(),p.getPrice())).collect(Collectors.toList());
    }


    @GetMapping("/{id}")
    public ProductDto findProducts(@PathVariable Long id) {
        Product p = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Продукт с id: "+id+" не найден"));
        return new ProductDto(p.getId(),p.getTitle(),p.getPrice());
//    public ResponseEntity<?> findProducts(@PathVariable Long id) {
//        Optional<Product> product = productService.findById(id);
//        if (!product.isPresent()){
//            ResponseEntity<MarketError> err =
//                    new ResponseEntity<>(new MarketError(HttpStatus.NOT_FOUND.value(),"Продукт с id: "+id+" не найден"),HttpStatus.NOT_FOUND);
//            return err;
//       }
//        return new ResponseEntity<>(product.get(), HttpStatus.OK );
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProducts(@PathVariable Long id) {
        productService.deleteById(id);
    }
}