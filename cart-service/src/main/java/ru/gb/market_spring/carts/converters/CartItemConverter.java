package ru.gb.market_spring.carts.converters;

import org.springframework.stereotype.Component;
import ru.gb.market_spring.api.CartItemDto;
import ru.gb.market_spring.carts.model.CartItem;

@Component
public class CartItemConverter {
    public CartItemDto entityCIToDto(CartItem cartItem){
        return new CartItemDto(
                cartItem.getProductId(),
                cartItem.getProductTitle(),
                cartItem.getQuantity(),
                cartItem.getPricePerProduct(),
                cartItem.getPrice());

//        CartItemDto cartItemDto = new CartItemDto();
//        cartItemDto.setPrice(cartItem.getPrice());
//        cartItemDto.setPricePerProduct(cartItem.getPricePerProduct());
//        cartItemDto.setQuantity(cartItem.getQuantity());
//        cartItemDto.setProductTitle(cartItem.getProductTitle());
//        cartItemDto.setProductId(cartItemDto.getProductId());
//        return cartItemDto;
    }
}
