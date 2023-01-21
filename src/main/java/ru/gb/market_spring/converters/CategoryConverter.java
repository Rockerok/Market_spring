package ru.gb.market_spring.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.gb.market_spring.dto.CategoryDto;
import ru.gb.market_spring.dto.ProductDto;
import ru.gb.market_spring.entities.Category;
import ru.gb.market_spring.entities.Product;
import ru.gb.market_spring.exceptions.ResourceNotFoundException;
import ru.gb.market_spring.services.CategoryService;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CategoryConverter {
    private final ProductConverter productConverter;

    public CategoryDto entityToTdo (Category category){
        CategoryDto c = new CategoryDto();
        c.setId(category.getId());
        c.setTitle(category.getTitle());
        c.setProducts(category.getProducts().stream().map(productConverter::entityToTdo).collect(Collectors.toList()));
        return c;
    }

}
