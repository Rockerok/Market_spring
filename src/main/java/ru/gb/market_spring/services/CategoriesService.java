package ru.gb.market_spring.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.market_spring.entities.Categories;
import ru.gb.market_spring.repositories.CategoriesRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoriesService {
    private final CategoriesRepository categoriesRepository;

    public Optional<Categories>findByTitle(String title){
        return categoriesRepository.findByTitle(title);
    }

}
