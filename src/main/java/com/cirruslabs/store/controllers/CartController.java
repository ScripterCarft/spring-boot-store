package com.cirruslabs.store.controllers;

import com.cirruslabs.store.dtos.AddItemToCartRequest;
import com.cirruslabs.store.dtos.CartDto;
import com.cirruslabs.store.dtos.CartItemDto;
import com.cirruslabs.store.dtos.UpdateCartItemRequest;
import com.cirruslabs.store.exceptions.ProductNotFoundException;
import com.cirruslabs.store.services.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/carts")
@Tag(name = "Carts")
public class CartController {
    private final CartService cartService;

    @PostMapping
    public ResponseEntity<CartDto> createCart(UriComponentsBuilder builder) {
        var cartDto = cartService.createCart();

        var uri = builder.path("/carts/{id}").buildAndExpand(cartDto.getId()).toUri();
        return ResponseEntity.created(uri).body(cartDto);
    }

    @PostMapping("/{id}/items")
    @Operation(summary = "Adds a product to the cart.")
    public ResponseEntity<CartItemDto> addToCart(UriComponentsBuilder builder,
                                                 @Parameter(description = "The ID of the cart.")
                                                 @PathVariable UUID id,
                                                 @RequestBody AddItemToCartRequest request) {
        var cartItemDto = cartService.addToCart(id, request.getProductId());

        var uri = builder.path("/carts/{cartId}/items/{productId}").buildAndExpand(id, request.getProductId()).toUri();
        return ResponseEntity.created(uri).body(cartItemDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartDto> getCart(@PathVariable UUID id) {
        var cartDto = cartService.getCart(id);

        return ResponseEntity.ok(cartDto);
    }

    @PutMapping("/{cartId}/items/{productId}")
    public ResponseEntity<?> updateItem(@PathVariable UUID cartId, @PathVariable Long productId, @Valid @RequestBody UpdateCartItemRequest request) {
        var cartItemDto = cartService.updateCartItem(cartId, productId, request.getQuantity());

        return ResponseEntity.ok(cartItemDto);
    }

    @DeleteMapping("/{cartId}/items/{productId}")
    public ResponseEntity<?> removeItem(@PathVariable UUID cartId, @PathVariable Long productId) {
        cartService.removeItem(cartId, productId);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{cartId}/items")
    public ResponseEntity<?> removeItems(@PathVariable UUID cartId) {
        cartService.removeItems(cartId);

        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleProductNotFoundException() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Product not found in the cart."));
    }
}
