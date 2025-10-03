package com.matheus.ecommerce.mapper;

import com.matheus.ecommerce.dto.CartItemRequestDTO;
import com.matheus.ecommerce.dto.CartItemResponseDTO;
import com.matheus.ecommerce.entity.Cart;
import com.matheus.ecommerce.entity.CartItem;
import com.matheus.ecommerce.entity.Product;

public class CartItemMapper {

    public static CartItem toEntity(CartItemRequestDTO dto, Cart cart) {
        CartItem item = new CartItem();
        item.setCart(cart);
        item.setProduct(new Product(dto.getProductId(), null, null, 0, 0)); // apenas ID
        item.setQuantity(dto.getQuantity());
        return item;
    }

    public static CartItemResponseDTO toDTO(CartItem item) {
        CartItemResponseDTO dto = new CartItemResponseDTO();
        dto.setId(item.getId());
        dto.setQuantity(item.getQuantity());
        dto.setProduct(ProductMapper.toDTO(item.getProduct()));
        return dto;
    }
}
