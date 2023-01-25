package ru.gb.market_spring.carts.integrations;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.gb.market_spring.api.ProductDto;
import ru.gb.market_spring.api.ResourceNotFoundException;

import javax.swing.text.html.Option;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductServiceIntegration {
//    private final RestTemplate restTemplate;
    private final WebClient productServiceWebClient;

//    public Optional<ProductDto> getProductById (Long id){
//        ProductDto productDto = productServiceWebClient.get()
//        return Optional.ofNullable(restTemplate.getForObject("http://localhost:8189/market_spring/api/v1/products/"+id, ProductDto.class));
        public ProductDto getProductById (Long id){
            return productServiceWebClient.get()
                .uri("/api/v1/products/"+id)
//                .header("username" ,username)
//                .bodyValue // for Post
//                    Насторойка запроса: хедеры, куки
                .retrieve()
                .onStatus(
                        httpStatus -> httpStatus.value() == HttpStatus.NOT_FOUND.value(),
                        clientResponse -> Mono.error(new ResourceNotFoundException("Товар не найден в продуктовом МС"))
                        )
//                .onStatus(HttpStatus::is4xxClientError, clientResponse -> Mono.error(new ResourceNotFoundException("Товар не найден в продуктовом МС"))
                .bodyToMono(ProductDto.class)
                .block();

    }
}
