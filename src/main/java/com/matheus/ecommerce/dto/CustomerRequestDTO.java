package com.matheus.ecommerce.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerRequestDTO {

    @NotBlank (message = "O nome é obrigatório.")
    private String name;

    @Email (message = "Email invalido!")
    @NotBlank (message = "O emial é obrigatório.")
    private String email;

    @NotBlank (message = "A senha é obrigatória.")
    @Size (min = 6, message = "Deve ter no minimo 6 caracteres.")
    private String password;

    @NotBlank (message = "O endereço é obrigatório.")
    private String address;
}
