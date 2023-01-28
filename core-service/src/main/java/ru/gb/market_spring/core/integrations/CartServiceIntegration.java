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
        public CartDto getCurrentCart (){
            return cartServiceWebClient.get()
                .uri("/api/v1/cart")
//                    Насторойка запроса: хедеры, куки
                .retrieve()
                .bodyToMono(CartDto.class)
                .block();
    }

    public void clear() {
        cartServiceWebClient.get()
            .uri("/api/v1/cart/clear")
            .retrieve()
            .toBodilessEntity()
            .block();
    }
}
