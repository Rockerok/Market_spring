package ru.gb.market_spring.carts.model;

import lombok.Data;
import ru.gb.market_spring.api.ProductDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public class Cart {
    private List<CartItem> items;
    private int totalPrice;

    public Cart(){
        this.items = new ArrayList<>();
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
    public void remove(Long productId){
        if(items.removeIf(item -> item.getProductId().equals(productId))) {
            recalculate();
        }
    }
    public void increaseQuantity (Long productId){
        for (CartItem item: items){
            if (item.getProductId().equals(productId)){
                item.changeQuantity(1);
                recalculate();
                return;
            }
        }
    }
    public void decreaseQuantity (Long productId){
        for (CartItem item: items){
            if (item.getProductId().equals(productId)){
                if (item.getQuantity()==1){
                    remove(productId);
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
        totalPrice=0;
    }
    private void recalculate(){
        totalPrice = 0;
        for (CartItem item: items){
            totalPrice+=item.getPrice();
        }
    }
}
