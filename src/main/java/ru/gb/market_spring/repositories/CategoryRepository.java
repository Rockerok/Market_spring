package ru.gb.market_spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.market_spring.entities.Category;
import ru.gb.market_spring.entities.Product;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByTitle(String title);

}
