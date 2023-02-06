package ru.gb.market_spring.entities;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public class Cart {
    private List<CartItem> items;
    private int quantityProd;
    private int totalPrice;

    public Cart(){
        this.items = new ArrayList<>();
    }
    public List<CartItem> getItems(){
        return Collections.unmodifiableList(items);
    }
    public void addProductToCart (Product product){ //TODO: Д/З
        int posProd=-1;
        for (CartItem item: items){
            if (item.getProductId()==(product.getId())){
                posProd = items.indexOf(item);
            }
        }
        if (posProd!=-1){
            CartItem cartItem = items.get(posProd);
            cartItem.setQuantity(cartItem.getQuantity()+1);
            cartItem.setPrice(cartItem.getPricePerProduct() * cartItem.getQuantity());
            items.set(posProd,cartItem);
        }else {
            items.add(new CartItem(product.getId(), product.getTitle(), 1, product.getPrice(), product.getPrice()));
        }
        recalculate();
    }

    public void deleteProductFromCart (Product product){
        for (CartItem item: items){
            if (item.getProductId()==(product.getId())){
                items.remove(this);
            }
        }
        recalculate();
    }

    private void recalculate(){
        totalPrice = 0;
        for (CartItem item: items){
            totalPrice+=item.getPrice();
        }
    }
}
