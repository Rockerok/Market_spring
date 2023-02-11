package ru.gb.market_spring.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.gb.market_spring.api.ProductDto;
import ru.gb.market_spring.api.ResourceNotFoundException;
import ru.gb.market_spring.core.entities.Category;
import ru.gb.market_spring.core.entities.Product;
import ru.gb.market_spring.core.repositories.ProductRepository;
import ru.gb.market_spring.core.repositories.specifications.ProductSpecifications;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

//    public List<Product> findAll (Specification<Product> spec, int page) {
//        return productRepository.findAll();
    public Page<Product> findAll (Specification<Product> spec, int page) {
        int sizePage = 10;
        return productRepository.findAll(spec, PageRequest.of(page, sizePage));
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
        Category category = categoryService.findByTitle(productDto.getCategoryTitle()).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        product.setCategory(category);
        productRepository.save(product);
        return product;
    }

    public Specification<Product> createSpecByFilters(Integer minPrice, Integer maxPrice, String title){
        Specification<Product> spec = Specification.where(null);
        if (minPrice != null){
            spec = spec.and(ProductSpecifications.priceGreaterOrEqualsThan(minPrice));
        }
        if (maxPrice != null){
            spec = spec.and(ProductSpecifications.priceLessThanOrEqualsThan(maxPrice));
        }
        if (title != null){
            spec = spec.and(ProductSpecifications.titleLike(title));
        }
        return spec;
    }
}
