package com.cirruslabs.store.controllers;

import com.cirruslabs.store.dtos.CheckoutRequest;
import com.cirruslabs.store.dtos.CheckoutResponse;
import com.cirruslabs.store.dtos.ErrorDto;
import com.cirruslabs.store.exceptions.CartEmptyException;
import com.cirruslabs.store.exceptions.CartNotFoundException;
import com.cirruslabs.store.services.CheckoutService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/checkout")
@AllArgsConstructor
public class CheckoutController {
    private final CheckoutService checkoutService;

    @PostMapping
    public CheckoutResponse checkout(@Valid @RequestBody CheckoutRequest checkoutRequest) {
        return checkoutService.checkout(checkoutRequest);
    }

    @ExceptionHandler({CartEmptyException.class, CartNotFoundException.class})
    public ResponseEntity<ErrorDto> handleException(Exception ex) {
        return ResponseEntity.badRequest().body(new ErrorDto(ex.getMessage()));
    }
}
