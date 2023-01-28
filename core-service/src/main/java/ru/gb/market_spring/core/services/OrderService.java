package ru.gb.market_spring.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.market_spring.api.CartDto;
//import ru.gb.market_spring.carts.model.Cart;
//import ru.gb.market_spring.carts.services.CartService;
import ru.gb.market_spring.core.entities.Order;
import ru.gb.market_spring.core.entities.OrderItem;
import ru.gb.market_spring.core.integrations.CartServiceIntegration;
import ru.gb.market_spring.core.repositories.OrderItemRepository;
import ru.gb.market_spring.core.repositories.OrderRepository;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
//    private final CartService cartService;
    private final  ProductService productService;
    private final OrderRepository ordersRepository;
    private final OrderItemRepository orderItemRepository;
    private final CartServiceIntegration cartServiceIntegration;

    @Transactional
    public void createOrder (String username) {
//        //TODO DZ-3 1:59:00
        CartDto cartDto = cartServiceIntegration.getCurrentCart();

        Order order = new Order();
        order.setUsername(username);
        order.setTotal_price(cartDto.getTotalPrice());
        order.setItems(cartDto.getItems().stream().map(
                cartItem -> new OrderItem(
                        productService.findById(cartItem.getProductId()).get(),
                        order,
                        cartItem.getQuantity(),
                        cartItem.getPricePerProduct(),
                        cartItem.getPrice()
                )
        ).collect(Collectors.toList()));
        ordersRepository.save(order);
        cartServiceIntegration.clear();
    }
}