package com.matheus.ecommerce.controller;

import com.matheus.ecommerce.dto.CartRequestDTO;
import com.matheus.ecommerce.dto.CartResponseDTO;
import com.matheus.ecommerce.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/carts")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartResponseDTO> getCartById(@PathVariable Long id) {
        return ResponseEntity.ok(cartService.getCartById(id));
    }

    @PostMapping
    public ResponseEntity<CartResponseDTO> saveCart(@RequestBody @Valid CartRequestDTO dto) {
        return ResponseEntity.ok(cartService.saveCart(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartResponseDTO> updateCart(@PathVariable Long id,
                                                      @RequestBody @Valid CartRequestDTO dto) {
        return ResponseEntity.ok(cartService.updateCart(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCart(@PathVariable Long id) {
        return ResponseEntity.ok(cartService.deleteCart(id));
    }

    @PutMapping("/{cartId}/remove/{productId}")
    public ResponseEntity<CartResponseDTO> removeProductFromCart(@PathVariable Long cartId,
                                                                 @PathVariable Long productId) {
        return ResponseEntity.ok(cartService.removeProductFromCart(cartId, productId));
    }

    @PutMapping("/{cartId}/update/{productId}")
    public ResponseEntity<CartResponseDTO> updateProductFromCart(@PathVariable Long cartId,
                                                                 @PathVariable Long productId,
                                                                 @RequestParam(defaultValue = "1") Integer quantity) {
        return ResponseEntity.ok(cartService.updateQuantityProduct(cartId, productId, quantity));
    }

    @PostMapping("/{cartId}/add/{productId}")
    public ResponseEntity<CartResponseDTO> addProductFromCart(@PathVariable Long cartId,
                                                              @PathVariable Long productId,
                                                              @RequestParam(defaultValue = "1") Integer quantity) {
        return ResponseEntity.ok(cartService.addProductToCart(cartId, productId, quantity));
    }
}