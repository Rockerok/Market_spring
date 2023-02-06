package ru.gb.market_spring.carts.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.gb.market_spring.api.CartDto;
import ru.gb.market_spring.api.CartItemDto;
import ru.gb.market_spring.carts.model.Cart;
import ru.gb.market_spring.carts.model.CartItem;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CartConverter {
    private final CartItemConverter cartItemConverter;

    public CartDto entityCToDto(Cart cart){
        return new CartDto(cart.getItems().stream().map(cartItemConverter::entityCIToDto).collect(Collectors.toList()), cart.getTotalPrice());

//        CartDto cartDto = new CartDto();
//        cartDto.setTotalPrice(cart.getTotalPrice());
//        cartDto.setItems(cart.getItems().stream().map(cartItemConverter::entityCIToDto).collect(Collectors.toList()));
//        return cartDto;
    }
}
