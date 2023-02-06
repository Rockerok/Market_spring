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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final  ProductService productService;
    private final OrderRepository orderRepository;
    private final CartServiceIntegration cartServiceIntegration;

    @Transactional
    public void createOrder (String username) {
//        //TODO DZ-3 1:59:00
        CartDto cartDto = cartServiceIntegration.getCurrentCart(username);
        if (cartDto.getItems().isEmpty()) {
            throw new IllegalStateException("Нельзя оформить заказ для пустой корзины");
        }
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

//        order.setItems(new ArrayList<>());
//        cartDto.getItems().forEach(ci -> {
//            OrderItem oi = new OrderItem();
//            oi.setOrder(order);
//            oi.setPrice(ci.getPrice());
//            oi.setQuantity(ci.getQuantity());
//            oi.setPricePerProduct(ci.getPricePerProduct());
//            oi.setProduct(productService.findById(ci.getProductId()).orElseThrow(() -> new ResourceNotFoundException("Product not found")));
//            order.getItems().add(oi);
//        });

        orderRepository.save(order);
        cartServiceIntegration.clear(username);
    }

    public List<Order> findUserOrders(String username) {
        return orderRepository.findAllByUsername(username);
    }
}