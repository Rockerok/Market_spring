package ru.gb.market_spring.services;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.market_spring.entities.*;
import ru.gb.market_spring.exceptions.ResourceNotFoundException;
import ru.gb.market_spring.repositories.OrderItemRepository;
import ru.gb.market_spring.repositories.OrderRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final  CartService cartService;
    private final  ProductService productService;

    private final  OrderRepository ordersRepository;
    private final  OrderItemRepository orderItemRepository;


    @Transactional
    public void createOrder (User user){
        //TODO DZ-3 1:59:00
        Cart cart = cartService.getCurrentCart();
        Order order = new Order();
        order.setUser(user);
        order.setTotal_price(cart.getTotalPrice());
//        order.setPhone();
//        order.setAddress();
        ordersRepository.save(order);

        List<OrderItem> orderItems = cart.getItems().stream().map(
                cartItem ->{
                    OrderItem orderItem =  new OrderItem();
                    orderItem.setProduct(productService.findById(cartItem.getProductId()).orElseThrow(
                            () -> new ResourceNotFoundException("Нельзя добавить продукт id:"
                                    +cartItem.getProductId()+ ". Такого продукта нет"))
                    );
                    orderItem.setOrder(order);
                    orderItem.setQuantity(cartItem.getQuantity());
                    orderItem.setPricePerProduct(cartItem.getPricePerProduct());
                    orderItem.setPrice(cartItem.getPrice());
                    orderItemRepository.save(orderItem);
                    return orderItem;
                }).collect(Collectors.toList());
    }
}