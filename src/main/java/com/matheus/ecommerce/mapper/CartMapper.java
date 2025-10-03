package com.matheus.ecommerce.mapper;

import com.matheus.ecommerce.dto.CartRequestDTO;
import com.matheus.ecommerce.dto.CartResponseDTO;
import com.matheus.ecommerce.entity.Cart;
import com.matheus.ecommerce.entity.Customer;

import java.util.stream.Collectors;

public class CartMapper {

    public static Cart toEntity(CartRequestDTO dto, Customer customer) {
        Cart cart = new Cart();
        cart.setCustomer(customer);
        if (dto.getItems() != null) {
            cart.setItems(
                    dto.getItems().stream()
                            .map(itemDTO -> CartItemMapper.toEntity(itemDTO, cart))
                            .collect(Collectors.toSet())
            );
        }
        return cart;
    }

    public static CartResponseDTO toDTO(Cart cart) {
        CartResponseDTO dto = new CartResponseDTO();
        dto.setId(cart.getId());
        dto.setCustomerId(cart.getCustomer().getId());
        dto.setItems(
                cart.getItems().stream()
                        .map(CartItemMapper::toDTO)
                        .collect(Collectors.toSet())
        );
        return dto;
    }
}