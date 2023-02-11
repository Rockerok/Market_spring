package ru.gb.market_spring.core.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.market_spring.core.entities.Product;
@Repository
public interface CartRepository extends JpaRepository<Product, Long> {
}
