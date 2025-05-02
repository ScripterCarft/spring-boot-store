package com.cirruslabs.store.services;

import com.cirruslabs.store.dtos.OrderDto;
import com.cirruslabs.store.exceptions.OrderNotFoundException;
import com.cirruslabs.store.mappers.OrderMapper;
import com.cirruslabs.store.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final AuthService authService;
    private final OrderMapper orderMapper;

    public List<OrderDto> getAllOrders() {
        var user = authService.getCurrentUser();
        var orders = orderRepository.getOrdersByCustomer(user);
        return orders.stream().map(orderMapper::toDto).toList();
    }

    public OrderDto getOrderById(long id) {
        var order = orderRepository.getOrderWithItems(id)
                .orElseThrow(OrderNotFoundException::new);

        var user = authService.getCurrentUser();
        if (!order.isPlacedBy(user)) {
            throw new AccessDeniedException("You dont have access to this order");
        }

        return orderMapper.toDto(order);
    }
}
