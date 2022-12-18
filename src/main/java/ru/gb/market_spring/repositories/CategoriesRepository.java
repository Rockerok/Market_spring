package ru.gb.market_spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.market_spring.entities.Categories;
import ru.gb.market_spring.entities.Product;

import java.util.Optional;

@Repository
public interface CategoriesRepository extends JpaRepository<Product, Long> {
    Optional<Categories> findByTitle(String title);

}
