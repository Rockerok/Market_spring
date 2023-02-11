package ru.gb.market_spring.carts.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.gb.market_spring.api.CartDto;
import ru.gb.market_spring.api.StringResponse;
import ru.gb.market_spring.carts.converters.CartConverter;
import ru.gb.market_spring.carts.services.CartService;

import java.util.UUID;


@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final CartConverter cartConverter;

    @GetMapping("/generate_id")
    public StringResponse generateGuestCartId() {
        return new StringResponse(UUID.randomUUID().toString());
    }
    @GetMapping("/{guestCartId}/add/{productId}")
    public void addToCart(
            @RequestHeader(required = false) String username,
            @PathVariable String guestCartId,
            @PathVariable Long productId
    ){
        String currentCartId = selectCartId(username, guestCartId);
        cartService.addCart(currentCartId,productId);
    }

    @GetMapping("/{guestCartId}")
    public CartDto getCurrentCart(
            @RequestHeader(required = false) String username,
            @PathVariable String guestCartId
    ){
        String currentCartId = selectCartId(username, guestCartId);
        return cartConverter.entityCToDto(cartService.getCurrentCart(currentCartId));
    }

    private String selectCartId(String username, String guestCartId) {
        if (username != null) {
            return username;
        }
        return guestCartId;
    }

    @GetMapping("/{guestCartId}/remove/{productId}")
    public void removeFromCart(
            @RequestHeader(required = false) String username,
            @PathVariable String guestCartId,
            @PathVariable Long productId
    ){
        String currentCartId = selectCartId(username, guestCartId);
        cartService.remove(currentCartId,productId);
    }

    @GetMapping("/{guestCartId}/clear")
    public void clearCart(
            @RequestHeader(required = false) String username,
            @PathVariable String guestCartId
    ){
        String currentCartId = selectCartId(username, guestCartId);
        cartService.clearCurrentCartId(currentCartId);
    }
}
