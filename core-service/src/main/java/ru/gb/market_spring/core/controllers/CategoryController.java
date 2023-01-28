package ru.gb.market_spring.core.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.market_spring.core.repositories.CategoryRepository;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CategoryController {
        private final CategoryRepository categoryRepository;
}
