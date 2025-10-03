package com.matheus.ecommerce.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductRequestDTO {

    @NotBlank (message = "O nome é obrigatório.")
    private String name;

    @NotBlank (message = "A descrição é obirgatória.")
    private String description;

    @Positive (message = "O preço deve ser maior que zero.")
    private double price;

    @Positive (message = "O stock deve ser maior que zero.")
    private int stock;
}
