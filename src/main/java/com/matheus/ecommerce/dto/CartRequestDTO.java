package com.matheus.ecommerce.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class CartRequestDTO {

    @NotNull (message = "O ID do cliente é obrigatório.")
    private Long customerId;

    private Set<CartItemRequestDTO> items;
}
