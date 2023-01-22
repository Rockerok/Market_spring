package ru.gb.market_spring.carts.integrations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.gb.market_spring.api.ProductDto;

import javax.swing.text.html.Option;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductServiceIntegration {
    private final RestTemplate restTemplate;

    public Optional<ProductDto> getProductById (Long id){
        return Optional.ofNullable(restTemplate.getForObject("http://localhost:8189/market_spring/api/v1/products/"+id, ProductDto.class));

    }
}
