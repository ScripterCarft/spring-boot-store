package com.cirruslabs.store.services;

import com.cirruslabs.store.entities.Order;

public interface PaymentGateway {
    CheckoutSession createCheckoutSession(Order order);
}
