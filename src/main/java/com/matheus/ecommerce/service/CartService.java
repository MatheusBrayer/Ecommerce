package com.matheus.ecommerce.service;

import com.matheus.ecommerce.dto.CartRequestDTO;
import com.matheus.ecommerce.dto.CartResponseDTO;
import com.matheus.ecommerce.entity.Cart;
import com.matheus.ecommerce.entity.Customer;
import com.matheus.ecommerce.entity.Product;
import com.matheus.ecommerce.mapper.CartMapper;
import com.matheus.ecommerce.repository.CartItemRepository;
import com.matheus.ecommerce.repository.CartRepository;
import com.matheus.ecommerce.repository.CustomerRepository;
import com.matheus.ecommerce.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final CustomerRepository customerRepository;

    public CartService(ProductRepository productRepository, CartRepository cartRepository,
                       CartItemRepository cartItemRepository, CustomerRepository customerRepository) {
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.customerRepository = customerRepository;
    }

    public CartResponseDTO getCartById(Long id) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ID não encontrado!"));
        return CartMapper.toDTO(cart);
    }

    public CartResponseDTO saveCart(CartRequestDTO dto) {
        Customer customer = customerRepository.findById(dto.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Cliente não cadastrado!"));

        boolean alreadyHasCart = cartRepository.findAll().stream()
                .filter(c -> c.getCustomer() != null)
                .anyMatch(c -> c.getCustomer().getId().equals(customer.getId()));

        if (alreadyHasCart) {
            throw new RuntimeException("Cliente já possui carrinho!");
        }

        Cart cart = CartMapper.toEntity(dto, customer);
        Cart saved = cartRepository.save(cart);
        return CartMapper.toDTO(saved);
    }

    public CartResponseDTO updateCart(Long id, CartRequestDTO dto) {
        Cart existing = cartRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ID não encontrado!"));

        Customer customer = customerRepository.findById(dto.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));

        Cart updated = CartMapper.toEntity(dto, customer);
        updated.setId(existing.getId());
        Cart saved = cartRepository.save(updated);
        return CartMapper.toDTO(saved);
    }

    public String deleteCart(Long id) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ID não encontrado!"));
        cartRepository.delete(cart);
        return "Carrinho deletado com sucesso!";
    }

    public CartResponseDTO removeProductFromCart(Long cartId, Long productId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Carrinho não encontrado!"));
        cart.getItems().removeIf(item -> item.getProduct().getId().equals(productId));
        Cart saved = cartRepository.save(cart);
        return CartMapper.toDTO(saved);
    }

    public CartResponseDTO updateQuantityProduct(Long cartId, Long productId, Integer quantity) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("ID não encontrado!"));
        cart.getItems().forEach(item -> {
            if (item.getProduct().getId().equals(productId)) {
                item.setQuantity(quantity);
                cartItemRepository.save(item);
            }
        });
        Cart saved = cartRepository.save(cart);
        return CartMapper.toDTO(saved);
    }

    public CartResponseDTO addProductToCart(Long cartId, Long productId, Integer quantity) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Carrinho não encontrado!"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado!"));

        cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst()
                .ifPresentOrElse(
                        item -> item.setQuantity(item.getQuantity() + quantity),
                        () -> cart.getItems().add(new com.matheus.ecommerce.entity.CartItem(null, product, cart, quantity))
                );

        Cart saved = cartRepository.save(cart);
        return CartMapper.toDTO(saved);
    }
}