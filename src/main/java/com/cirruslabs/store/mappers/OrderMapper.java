package com.cirruslabs.store.mappers;

import com.cirruslabs.store.dtos.OrderDto;
import com.cirruslabs.store.entities.Order;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {
    Order toEntity(OrderDto orderDto);

    OrderDto toDto(Order order);

    Order update(OrderDto orderDto, @MappingTarget Order order);
}