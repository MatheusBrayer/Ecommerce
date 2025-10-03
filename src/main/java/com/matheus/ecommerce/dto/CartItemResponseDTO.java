package com.matheus.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemResponseDTO {

    private Long id;
    private ProductResponseDTO product;
    private Integer quantity;
}
