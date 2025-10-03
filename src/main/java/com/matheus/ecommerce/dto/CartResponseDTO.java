package com.matheus.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class CartResponseDTO {

    private Long id;
    private Long customerId;
    private Set<CartItemResponseDTO> items;
}
