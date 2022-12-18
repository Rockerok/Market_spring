package ru.gb.market_spring.entities;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class Cart {
    private List<Product> productCart;

    public List<Product> getCart() {
        return productCart;
    }
    public void setProductCart(Product product) {
        productCart.add(product);
    }

    @Autowired
    public Cart(List<Product> productCart) {
        productCart = new ArrayList<>();
        this.productCart = productCart;
    }

    public void setRemoveProd(Long id) {
        try {
            productCart.remove(id);
        } catch (IndexOutOfBoundsException iobe){
            System.out.println("Product not delete from Cart");
        }
    }
}
