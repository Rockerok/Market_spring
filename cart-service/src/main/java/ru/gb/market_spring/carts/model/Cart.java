package ru.gb.market_spring.carts.model;

import lombok.Data;
import ru.gb.market_spring.api.ProductDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public class Cart {
    private List<CartItem> items;
    private BigDecimal totalPrice;

    public Cart(){
        this.items = new ArrayList<>();
        this.totalPrice = BigDecimal.ZERO;
    }
    public List<CartItem> getItems(){
        return Collections.unmodifiableList(items);
    }
    public void addProductToCart (ProductDto product){ //TODO: Д/З
        for (CartItem item: items) {
            if (item.getProductId().equals(product.getId())) {
                item.changeQuantity(1);
                recalculate();
                return;
            }
        }
        items.add(new CartItem(product.getId(), product.getTitle(), 1, product.getPrice(), product.getPrice()));
        recalculate();
    }
    public void remove(ProductDto product){
        if(items.removeIf(item -> item.getProductId().equals(product.getId()))) {
            recalculate();
        }
    }
    public void increaseQuantity (ProductDto product){
        for (CartItem item: items){
            if (item.getProductId().equals(product.getId())){
                item.changeQuantity(1);
                recalculate();
                return;
            }
        }
    }
    public void decreaseQuantity (ProductDto product){
        for (CartItem item: items){
            if (item.getProductId().equals(product.getId())){
                if (item.getQuantity()==1){
                    remove(product);
                }else {
                    item.changeQuantity(-1);
                }
                recalculate();
                return;
            }
        }
    }
    public void clear(){
        items.clear();
        totalPrice= BigDecimal.ZERO;
    }
    private void recalculate(){
        totalPrice= BigDecimal.ZERO;
        for (CartItem item: items){
            totalPrice.add(item.getPrice());
        }
    }
}
