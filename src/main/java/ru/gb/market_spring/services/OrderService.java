package ru.gb.market_spring.services;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.market_spring.dto.OrderDto;
import ru.gb.market_spring.entities.*;
import ru.gb.market_spring.repositories.OrdersItemsRepository;
import ru.gb.market_spring.repositories.OrdersRepository;

import java.time.LocalDateTime;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class OrderService {
    private ProductService productService;
    private CartService cartService;
    private OrdersRepository ordersRepository;
    private OrdersItemsRepository orderItemRepository;


    public void createOrder (User user){
        //TODO DZ-3 1:59:00
        Cart cart = cartService.getCurrentCart();
        Order order = new Order();
        int totalPrice=0;
        order.setUser(user);
//        order.setItems(orderItemRepository.findById(ordersRepository.findBy(order)));
        for (OrderItem items: order.getItems()
             ) {
            totalPrice+= items.getPrice();
        }
        order.setTotal_price(totalPrice);
        createOrderItems(cart,order);
//        ordersRepository.save(order);
    }

    private void createOrderItems(Cart cart, Order order) {
        OrderItem orderItem = (OrderItem) cart.getItems().stream().map(cartItem -> new OrderItem(
                null,
                productService.findById(cartItem.getProductId()).orElseThrow(() -> new RuntimeException()), //todo RuntimeEx
                order,
                cartItem.getQuantity(),
                cartItem.getPricePerProduct(),
                cartItem.getPrice(),
                LocalDateTime.now(),
                LocalDateTime.now()
        ));


//        orderItemRepository.save(orderItem);
    }
}
