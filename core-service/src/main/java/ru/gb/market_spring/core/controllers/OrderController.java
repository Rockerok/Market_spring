package ru.gb.market_spring.core.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.gb.market_spring.api.OrderDto;
import ru.gb.market_spring.core.converters.OrderConverter;
import ru.gb.market_spring.core.services.OrderService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final OrderConverter orderConverter;


    @GetMapping
    public List<OrderDto> getUserOrders(@RequestHeader String username) {
        return orderService.findUserOrders(username).stream().map(orderConverter::entityToDto).collect(Collectors.toList());
    }
    @PostMapping //("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder (@RequestHeader String username){
        //TODO DZ-3 1:55:00
        orderService.createOrder(username);
    }
}
