package ru.gb.market_spring.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.market_spring.api.CartDto;
import ru.gb.market_spring.api.ResourceNotFoundException;
//import ru.gb.market_spring.carts.model.Cart;
//import ru.gb.market_spring.carts.services.CartService;
import ru.gb.market_spring.core.entities.Order;
import ru.gb.market_spring.core.entities.OrderItem;
import ru.gb.market_spring.core.entities.User;
import ru.gb.market_spring.core.repositories.OrderItemRepository;
import ru.gb.market_spring.core.repositories.OrderRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
//    private final CartService cartService;
    private final  ProductService productService;
    private final OrderRepository ordersRepository;
    private final OrderItemRepository orderItemRepository;

    @Transactional
    public void createOrder (User user) {
//        //TODO DZ-3 1:59:00
        CartDto cartDto = null; // cartServiceIntegration.getCurrentCart();

        Order order = new Order();
        order.setUser(user);
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
//        cartServiceIntegration.clear();
    }
//        order.setPhone();
//        order.setAddress();
//        ordersRepository.save(order);
//        List<OrderItem> orderItems = cart.getItems().stream().map(
//                cartItem ->{
//                    OrderItem orderItem =  new OrderItem();
//                    orderItem.setProduct(productService.findById(cartItem.getProductId()).orElseThrow(
//                            () -> new ResourceNotFoundException("Нельзя добавить продукт id:"
//                                    +cartItem.getProductId()+ ". Такого продукта нет"))
//                    );
//                    orderItem.setOrder(order);
//                    orderItem.setQuantity(cartItem.getQuantity());
//                    orderItem.setPricePerProduct(cartItem.getPricePerProduct());
//                    orderItem.setPrice(cartItem.getPrice());
//                    orderItemRepository.save(orderItem);
//                    return orderItem;
//                }).collect(Collectors.toList());
}