package ru.gb.market_spring.carts.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import ru.gb.market_spring.api.ProductDto;
import ru.gb.market_spring.carts.integrations.ProductServiceIntegration;
import ru.gb.market_spring.carts.listenerStat.Event;
import ru.gb.market_spring.carts.listenerStat.EventPool;
import ru.gb.market_spring.carts.model.Cart;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Consumer;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartService {

        private final ProductServiceIntegration productServiceIntegration;
        private final RedisTemplate <String,Object> redisTemplate;
        @Value("${cart-service.cart-prefix}")
        private String cartprefix;

//        private Map<String, Cart> tempCart;
//        @PostConstruct
//        public void init(){
//            tempCart=new HashMap<>();
//        }

        public Cart getCurrentCart(String cartId){
            String TargetCarId = cartprefix + cartId;
//            if (!tempCart.containsKey(TargetCarId)) {
//
////             //   Identity Map Коллекция объектов
//                instance.tempCart.put(TargetCarId, new Cart());
////                tempCart.put(TargetCarId, new Cart());
//            }
//            return tempCart.get(TargetCarId);
            if(!redisTemplate.hasKey(TargetCarId)){
                redisTemplate.opsForValue().set(TargetCarId,new Cart());
            }
            return (Cart)redisTemplate.opsForValue().get(TargetCarId);
        }

        public void addCart(String cartId, Long productId){
            ProductDto product = productServiceIntegration.getProductById(productId);
            execute(cartId, cart -> cart.addProductToCart(product));
            cartStat(product);
//            Cart cart = getCurrentCart(cartId);
//            cart.addProductToCart(product);
//            redisTemplate.opsForValue().set(cartprefix + cartId,cart);
//                    .orElseThrow(() ->
//                            new ResourceNotFoundException("не удалось добавить продукт с id:"+productId+" в корзину. Продукт не найден"));
//            tempCart.addProductToCart(product);
//            getCurrentCart(cartId).addProductToCart(product);
        }

        // Spring Arch_HW5 сбор статистики (какие продукты добавляются в корзину) в Лог файл
    private void cartStat(ProductDto product) {
        EventPool eventPool = new EventPool();
        eventPool.registerListener(event -> log.toString());
        eventPool.start();
        eventPool.publishEvent(new Event(product.getTitle()));
    }

    public void remove(String cartId, Long productId){
            ProductDto product = productServiceIntegration.getProductById(productId);
            execute(cartId, cart -> cart.remove(product));
//            Cart cart = getCurrentCart(cartId);
//            cart.remove(product);
//            redisTemplate.opsForValue().set(cartprefix + cartId,cart);
//            getCurrentCart(cartId).remove(product);
        }
        public void clearCurrentCartId(String cartId){
            execute(cartId, Cart::clear);
//            Cart cart = getCurrentCart(cartId);
//            cart.clear();
//            redisTemplate.opsForValue().set(cartprefix + cartId,cart);
//            getCurrentCart(cartId).clear();
        }

        private void execute (String cartId, Consumer<Cart> operation){
            Cart cart = getCurrentCart(cartId);
            operation.accept(cart);
            redisTemplate.opsForValue().set(cartprefix + cartId,cart);
        }
}
