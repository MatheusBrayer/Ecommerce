package com.matheus.ecommerce.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table (name = "cart_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn (name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn (name = "cart_id")
    private Cart cart;

    private Integer quantity;
}
