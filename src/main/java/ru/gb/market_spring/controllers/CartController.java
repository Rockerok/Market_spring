package ru.gb.market_spring.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.gb.market_spring.entities.Cart;
import ru.gb.market_spring.services.CartService;


@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    @GetMapping("/add/{id}")
    public void addToCart(@PathVariable Long id){
        cartService.addCart(id);
    }

    @GetMapping
    public Cart getCurrentCart(){
        return cartService.getCurrentCart();
    }

    @GetMapping("/remove/{id}")
    public void removeFromCart(@PathVariable Long id){
        cartService.remove(id);
    }

    @GetMapping("/clear")
    public void clearCart(){
        cartService.clear();
    }
    @GetMapping("/new")
    public Cart newCart(){
        cartService.init();
        return cartService.getCurrentCart();
    }
}
