package ru.gb.market_spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.market_spring.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository <Product, Long> {
}
