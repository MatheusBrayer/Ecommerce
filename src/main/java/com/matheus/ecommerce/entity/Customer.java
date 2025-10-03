package com.matheus.ecommerce.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table (name = "customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    private String address;

}
