package com.matheus.ecommerce.repository;

import com.matheus.ecommerce.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository <CartItem, Long> {
}
