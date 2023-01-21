package ru.gb.market_spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    private long id;
    private String title;
    private List <ProductDto> products;
}
