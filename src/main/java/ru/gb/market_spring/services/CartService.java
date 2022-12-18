package ru.gb.market_spring.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.market_spring.entities.Cart;
import ru.gb.market_spring.entities.Product;
import ru.gb.market_spring.repositories.CartRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private Cart cart;

    public Cart cartAddProduct(Product product) {
        cart.setProductCart(product);
        return cart;
    }

    public void deleteCartProductById(Long id) {
        cart.setRemoveProd(id);
    }

    public List<Product> findAllFromCart () {
        return cartRepository.findAll();
    }
}
