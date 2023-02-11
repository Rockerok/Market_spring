package ru.gb.market_spring.core.integrations;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.gb.market_spring.api.CartDto;
import ru.gb.market_spring.api.ProductDto;
import ru.gb.market_spring.api.ResourceNotFoundException;

@Component
@RequiredArgsConstructor
public class CartServiceIntegration {
    private final WebClient cartServiceWebClient;
        public CartDto getCurrentCart (String username){
            return cartServiceWebClient.get()
                .uri("/api/v1/cart/0")
                .header("username", username)
//                    Насторойка запроса: хедеры, куки
                .retrieve()
                .bodyToMono(CartDto.class)
                .block();
    }

    public void clear(String username) {
        cartServiceWebClient.get()
            .uri("/api/v1/cart/0/clear")
            .header("username", username)
            .retrieve()
            .toBodilessEntity()
            .block();
    }
}
