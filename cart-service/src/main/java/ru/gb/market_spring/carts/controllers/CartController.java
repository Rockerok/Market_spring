package ru.gb.market_spring.carts.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.gb.market_spring.api.CartDto;
import ru.gb.market_spring.carts.converters.CartConverter;
import ru.gb.market_spring.carts.model.Cart;
import ru.gb.market_spring.carts.services.CartService;


@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CartController {
    private final CartService cartService;
    private final CartConverter cartConverter;
    @GetMapping("/add/{id}")
    public void addToCart(@PathVariable Long id){
        cartService.addCart(id);
    }

    @GetMapping
    public CartDto getCurrentCart(){
        return cartConverter.entityCToDto(cartService.getCurrentCart());
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
