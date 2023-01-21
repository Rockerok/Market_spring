package ru.gb.market_spring.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.market_spring.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
