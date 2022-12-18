package ru.gb.market_spring.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.market_spring.repositories.CategoriesRepository;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CategoriesController {
        private final CategoriesRepository categoriesRepository;
}
