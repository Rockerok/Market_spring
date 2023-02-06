package ru.gb.market_spring.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.market_spring.entities.Cart;
import ru.gb.market_spring.entities.Product;
import ru.gb.market_spring.exceptions.ResourceNotFoundException;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class CartService {
        private Cart tempCart;
        private final ProductService productService;

        @PostConstruct
        public void init(){
            tempCart=new Cart();
        }

        public Cart getCurrentCart(){
            return tempCart;
        }

        public void addCart(Long productId){
            Product product = productService.findById(productId)
                    .orElseThrow(() ->
                            new ResourceNotFoundException("не удалось добавить продукт с id:"+productId+" в корзину. Продукт не найден"));
            tempCart.addProductToCart(product);
        }

        public void deleteCartProductById(Long productId){
            Product product = productService.findById(productId)
                    .orElseThrow(() ->
                            new ResourceNotFoundException("не удалось удалить продукт с id:"+productId+" из корзины. Продукт не найден"));
            tempCart.deleteProductFromCart(product);
        }
}
