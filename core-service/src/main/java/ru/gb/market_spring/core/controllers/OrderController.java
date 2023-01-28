package ru.gb.market_spring.core.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.gb.market_spring.core.services.OrderService;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
 //todo go to MS Auth

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder (@RequestHeader String username){
//        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        //TODO DZ-3 1:55:00
        orderService.createOrder(username);
//        orderService.createOrder(user /*,orderData*/);
    }
}
