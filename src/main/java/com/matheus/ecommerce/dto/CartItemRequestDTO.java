package com.matheus.ecommerce.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemRequestDTO {

    @NotNull(message = "O ID do produto é obrigatório!")
    private Long productId;

    @NotNull(message = "A quantidade é obrigatória.")
    @Positive (message = "A quantidade deve ser maior que zero.")
    private Integer quantity;
}
