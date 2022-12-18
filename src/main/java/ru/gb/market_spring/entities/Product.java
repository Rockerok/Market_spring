package ru.gb.market_spring.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

//    @ManyToOne
//    @JoinColumn(name = "categories_id")
//    private Categories categories;
}
