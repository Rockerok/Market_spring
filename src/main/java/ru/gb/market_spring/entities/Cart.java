package ru.gb.market_spring.entities;

import lombok.Data;

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
    public void addProductToCart (Product product){ //TODO: Д/З
        if (increaseQuantity(product)) { return; }
        items.add(new CartItem(product.getId(), product.getTitle(), 1, product.getPrice(), product.getPrice()));
        recalculate();
    }
    public void deleteProductFromCart (Product product){
        items.removeIf(item -> item.getProductId().equals(product.getId()));
        recalculate();
    }
    public boolean increaseQuantity (Product product){
        for (CartItem item: items){
            if (item.getProductId().equals(product.getId())){
                item.setQuantity(item.getQuantity()+1);
                item.setPrice(item.getPricePerProduct() * item.getQuantity());
                recalculate();
                return true;
            }
        }
        return false;
    }
    public void decreaseQuantity (Product product){
        for (CartItem item: items){
            if (item.getProductId().equals(product.getId())){
                if (item.getQuantity()==1){
                    deleteProductFromCart (product);
                }else {
                    item.setQuantity(item.getQuantity()-1);
                    item.setPrice(item.getPricePerProduct() * item.getQuantity());
                }
                recalculate();
                return;
            }
        }
    }
    private void recalculate(){
        totalPrice = 0;
        for (CartItem item: items){
            totalPrice+=item.getPrice();
        }
    }
}
