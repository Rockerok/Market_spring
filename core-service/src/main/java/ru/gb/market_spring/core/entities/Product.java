package ru.gb.market_spring.core.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "products")
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private long id;

    @Column (name = "title")
    private String title;

    @Column (name = "price")
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "categories_id")
    private Category category;

    @CreationTimestamp
    @Column (name = "created_at")
    private LocalDateTime createdAt;

    @CreationTimestamp
    @Column (name = "updated_at")
    private LocalDateTime updateAt;


    // Реализация Билдера с помощью Плагина
    private Product(Builder builder) {
        setId(builder.id);
        setTitle(builder.title);
        setPrice(builder.price);
        setCategory(builder.category);
        setCreatedAt(builder.createdAt);
        setUpdateAt(builder.updateAt);
    }

    public static Builder newBuilder() {
        return new Builder();
    }
    private void Builder() {
    }

    public static Builder newBuilder(Product copy) {
        Builder builder = new Builder();
        builder.id = copy.getId();
        builder.title = copy.getTitle();
        builder.price = copy.getPrice();
        builder.category = copy.getCategory();
        builder.createdAt = copy.getCreatedAt();
        builder.updateAt = copy.getUpdateAt();
        return builder;
    }


    public static final class Builder {
        private long id;
        private String title;
        private BigDecimal price;
        private Category category;
        private LocalDateTime createdAt;
        private LocalDateTime updateAt;



        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public Builder category(Category category) {
            this.category = category;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder updateAt(LocalDateTime updateAt) {
            this.updateAt = updateAt;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }
}
