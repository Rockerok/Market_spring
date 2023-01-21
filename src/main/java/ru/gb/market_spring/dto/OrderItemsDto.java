package ru.gb.market_spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gb.market_spring.entities.Order;
import ru.gb.market_spring.entities.OrderItem;
import ru.gb.market_spring.entities.Product;
import ru.gb.market_spring.entities.User;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemsDto {
    private Long id;
    private Product product;
    private Order order;
    private int quantity;
    private int pricePerProduct;
    private int price;
}
