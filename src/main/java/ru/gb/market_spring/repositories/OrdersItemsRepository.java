package ru.gb.market_spring.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.market_spring.entities.Product;

public interface OrdersItemsRepository extends JpaRepository<Product, Long> {
}
