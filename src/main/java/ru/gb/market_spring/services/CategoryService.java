package ru.gb.market_spring.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.market_spring.entities.Category;
import ru.gb.market_spring.repositories.CategoryRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Optional<Category>findByTitle(String title){
        return categoryRepository.findByTitle(title);
    }

}
