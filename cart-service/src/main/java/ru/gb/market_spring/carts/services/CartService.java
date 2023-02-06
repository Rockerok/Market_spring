package ru.gb.market_spring.carts.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.market_spring.api.ProductDto;
import ru.gb.market_spring.carts.services.integrations.ProductServiceIntegration;
import ru.gb.market_spring.carts.model.Cart;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CartService {
        private Map<String, Cart> tempCart;
        private final ProductServiceIntegration productServiceIntegration;

        @PostConstruct
        public void init(){
            tempCart=new HashMap<>();
        }

        public Cart getCurrentCart(String cartId){
            if (!tempCart.containsKey(cartId)) {
                Cart cart = new Cart();
                tempCart.put(cartId, cart);
            }
            return tempCart.get(cartId);
        }

        public void addCart(String cartId, Long productId){
            ProductDto product = productServiceIntegration.getProductById(productId);
//                    .orElseThrow(() ->
//                            new ResourceNotFoundException("не удалось добавить продукт с id:"+productId+" в корзину. Продукт не найден"));
//            tempCart.addProductToCart(product);

            getCurrentCart(cartId).addProductToCart(product);
        }

        public void remove(String cartId, Long productId){
            ProductDto product = productServiceIntegration.getProductById(productId);
            getCurrentCart(cartId).remove(product);
        }
        public void clearCurrentCartId(String cartId){
            getCurrentCart(cartId).clear();
        }
}
