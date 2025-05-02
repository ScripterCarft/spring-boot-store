package com.cirruslabs.store.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link com.cirruslabs.store.entities.Product}
 */
@AllArgsConstructor
@Data
public class ProductDto implements Serializable {
    Long id;
    String name;
    String description;
    BigDecimal price;
    Byte categoryId;
}