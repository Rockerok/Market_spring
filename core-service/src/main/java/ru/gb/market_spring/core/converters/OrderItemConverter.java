package ru.gb.market_spring.core.converters;

import org.springframework.stereotype.Component;
import ru.gb.market_spring.api.OrderItemDto;
import ru.gb.market_spring.core.entities.OrderItem;


@Component
public class OrderItemConverter {
    public OrderItemDto entityToDto(OrderItem o) {
        return new OrderItemDto(
                o.getProduct().getId(),
                o.getProduct().getTitle(),
                o.getQuantity(),
                o.getPricePerProduct(),
                o.getPrice()
        );
    }
}
