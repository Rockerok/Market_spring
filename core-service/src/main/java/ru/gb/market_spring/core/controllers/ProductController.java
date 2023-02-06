package ru.gb.market_spring.core.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;
import ru.gb.market_spring.api.ProductDto;
import ru.gb.market_spring.api.ResourceNotFoundException;
import ru.gb.market_spring.core.converters.ProductConverter;
import ru.gb.market_spring.core.entities.Product;
import ru.gb.market_spring.core.services.ProductService;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductConverter productConverter;

//    @GetMapping
//    public List<ProductDto> findAllProducts() {
//        return productService.findAll().stream().map(productConverter::entityToTdo).collect(Collectors.toList());
//    }

    @GetMapping
    public List<ProductDto> findProducts(
            @RequestParam(required = false, name = "min_price") Integer minPrice,
            @RequestParam(required = false, name = "max_price") Integer maxPrice,
            @RequestParam(required = false, name = "title") String title,
            @RequestParam(defaultValue = "1", name = "p") Integer page
    ) {
        if (page<1) { page  =1; }
        Specification<Product> spec = productService.createSpecByFilters(minPrice, maxPrice, title);
//        return productService.findAll().stream().map(productConverter::entityToTdo).collect(Collectors.toList());
        return productService.findAll(spec,page-1).map(productConverter::entityToTdo).getContent();
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