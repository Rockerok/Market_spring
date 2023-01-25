package ru.gb.market_spring.carts.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.market_spring.api.ProductDto;
import ru.gb.market_spring.api.ResourceNotFoundException;
import ru.gb.market_spring.carts.integrations.ProductServiceIntegration;
import ru.gb.market_spring.carts.model.Cart;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class CartService {
        private Cart tempCart;
        private final ProductServiceIntegration productServiceIntegration;

        @PostConstruct
        public void init(){
            tempCart=new Cart();
        }

        public Cart getCurrentCart(){
            return tempCart;
        }

        public void addCart(Long productId){
            ProductDto product = productServiceIntegration.getProductById(productId);
//                    .orElseThrow(() ->
//                            new ResourceNotFoundException("не удалось добавить продукт с id:"+productId+" в корзину. Продукт не найден"));
            tempCart.addProductToCart(product);
        }

        public void remove(Long productId){
            tempCart.remove(productId);
        }
        public void clear(){
            tempCart.clear();
        }
}
