package ru.gb.market_spring.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.gb.market_spring.entities.Cart;
import ru.gb.market_spring.entities.Product;
import ru.gb.market_spring.services.CartService;
import java.util.List;


@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping("/Cart")
    public List<Product> findAllFromCart() {
        return cartService.findAllFromCart();
    }

    @PutMapping("/Cart/add/{id}")
    public List<Product> cartAddById(Product ProductId){
        cartService.cartAddProduct(ProductId);
        return findAllFromCart();
    }

    @DeleteMapping("/Cart/delete/{id}")
    public int deleteCardProductById(@PathVariable Long id){
        cartService.deleteCartProductById(id);
        return HttpStatus.OK.value();
    }
}
