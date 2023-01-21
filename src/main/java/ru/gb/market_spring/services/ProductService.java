package ru.gb.market_spring.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.market_spring.dto.ProductDto;
import ru.gb.market_spring.entities.Category;
import ru.gb.market_spring.entities.Product;
import ru.gb.market_spring.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public List<Product> findAll () {
        return productRepository.findAll();
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public void  deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public Product createNewProduct (ProductDto productDto){
        Product product = new Product();
        product.setPrice(productDto.getPrice());
        product.setTitle(productDto.getTitle());
        Category category = categoryService.findByTitle(productDto.getCategoryTitle()).orElseThrow(() -> new RuntimeException("Category not found"));
        product.setCategory(category);
        productRepository.save(product);
        return product;
    }
}
