package ru.gb.market_spring.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.gb.market_spring.dto.ProductDto;
import ru.gb.market_spring.entities.Category;
import ru.gb.market_spring.entities.Product;
import ru.gb.market_spring.exceptions.ResourceNotFoundException;
import ru.gb.market_spring.services.CategoryService;

@Component
@RequiredArgsConstructor
public class OrderConverter {
    private final CategoryService categoryService;
    public ProductDto entityToTdo (Product product){
        return new ProductDto(product.getId(), product.getTitle(), product.getPrice(), product.getCategory().getTitle());
    }

    public Product tdoToEntity (ProductDto productDto){
        Product p = new Product();
        p.setId(productDto.getId());
        p.setTitle(productDto.getTitle());
        p.setPrice(productDto.getPrice());
        Category c = categoryService.findByTitle(productDto.getTitle()).orElseThrow(() -> new ResourceNotFoundException("Категория не найдена"));
        p.setCategory(c);
        return p;
    }
}
