package ru.gb.market_spring.core.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
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
    private int price;

    @ManyToOne
    @JoinColumn(name = "categories_id")
    private Category category;

    @CreationTimestamp
    @Column (name = "created_at")
    private LocalDateTime createdAt;

    @CreationTimestamp
    @Column (name = "updated_at")
    private LocalDateTime updateAt;
}
