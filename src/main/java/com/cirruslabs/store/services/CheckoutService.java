package com.cirruslabs.store.services;

import com.cirruslabs.store.dtos.CheckoutRequest;
import com.cirruslabs.store.dtos.CheckoutResponse;
import com.cirruslabs.store.entities.Order;
import com.cirruslabs.store.exceptions.CartEmptyException;
import com.cirruslabs.store.exceptions.CartNotFoundException;
import com.cirruslabs.store.exceptions.PaymentException;
import com.cirruslabs.store.repositories.CartRepository;
import com.cirruslabs.store.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CheckoutService {
    private final CartRepository cartRepository;
    private final AuthService authService;
    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final PaymentGateway paymentGateway;

    @Transactional
    public CheckoutResponse checkout(CheckoutRequest checkoutRequest) {
        var cart = cartRepository.getCartWithItems(checkoutRequest.getCartId()).orElse(null);
        if (cart == null) {
            throw new CartNotFoundException();
        }

        if (cart.isEmpty()) {
            throw new CartEmptyException();
        }

        var order = Order.fromCart(cart, authService.getCurrentUser());

        orderRepository.save(order);

        try {
            var session = paymentGateway.createCheckoutSession(order);

            cartService.removeItems(cart.getId());

            return new CheckoutResponse(order.getId(), session.getCheckoutUrl());
        } catch (PaymentException ex) {
            orderRepository.delete(order);
            throw ex;
        }
    }
}
