package ru.gb.market_spring.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.market_spring.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
