package ru.gb.market_spring.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.gb.market_spring.entities.Order;
import ru.gb.market_spring.entities.Product;
import ru.gb.market_spring.entities.User;
import ru.gb.market_spring.repositories.OrdersRepository;
import ru.gb.market_spring.services.OrderService;
import ru.gb.market_spring.services.UserService;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;


    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder (Principal principal /*, @RequestBody OrderData orderData */){
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException());
        //TODO DZ-3 1:55:00
        orderService.createOrder(user /*,orderData*/);
    }
}
