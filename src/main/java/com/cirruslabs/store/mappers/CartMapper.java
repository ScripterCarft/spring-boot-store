package com.cirruslabs.store.mappers;

import com.cirruslabs.store.dtos.CartDto;
import com.cirruslabs.store.dtos.CartItemDto;
import com.cirruslabs.store.entities.Cart;
import com.cirruslabs.store.entities.CartItem;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CartMapper {
    Cart toEntity(CartDto cartDto);

    @Mapping(target = "totalPrice", expression = "java(cart.getTotal())")
    CartDto toDto(Cart cart);

    @Mapping(target = "totalPrice", expression = "java(cartItem.calculateTotal())")
    CartItemDto toDto(CartItem cartItem);

    Cart update(CartDto cartDto, @MappingTarget Cart cart);
}