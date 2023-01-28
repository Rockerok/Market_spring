package ru.gb.market_spring.core.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @Column(name= "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "username")
    private String username;

    @OneToMany (mappedBy = "order", cascade = CascadeType.PERSIST)
    private List<OrderItem> items;

    @Column (name= "address")
    private int address;

    @Column (name= "phone")
    private int phone;

    @Column (name = "total_price")
    private int total_price;

    @CreationTimestamp
    @Column (name = "created_at")
    private LocalDateTime createdAt;

    @CreationTimestamp
    @Column (name = "updated_at")
    private LocalDateTime updateAt;
}
