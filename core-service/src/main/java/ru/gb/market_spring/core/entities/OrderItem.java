package ru.gb.market_spring.core.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table (name="order_items")
@Data
@NoArgsConstructor
public class OrderItem {
    @Id
    @Column (name= "id")
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn (name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn (name = "order_id")
    private Order order;

    @Column (name= "quantity")
    private int quantity;

    @Column (name = "price_per_product")
    private BigDecimal pricePerProduct;

    @Column (name = "price")
    private BigDecimal price;

    @CreationTimestamp
    @Column (name = "created_at")
    private LocalDateTime createdAt;

    @CreationTimestamp
    @Column (name = "updated_at")
    private LocalDateTime updateAt;

    public OrderItem(Product product, Order order, int quantity, BigDecimal pricePerProduct, BigDecimal price) {
        this.product = product;
        this.order = order;
        this.quantity = quantity;
        this.pricePerProduct = pricePerProduct;
        this.price = price;
    }
}
